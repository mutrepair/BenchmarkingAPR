#!/usr/bin/env python3 -u
# Copyright (c) 2017-present, Facebook, Inc.
# All rights reserved.
#
# This CoCoNut code is licensed under the license found in the LICENSE file in
# the root directory of this CoCoNut tree. An additional grant of patent rights
# can be found in the PATENTS file in the same directory.
"""
Translate pre-processed data with a trained model.
"""
import codecs
import os
import torch
import numpy as np
from fairseq import  bleu,data, options, progress_bar, tasks, tokenizer, utils
from fairseq.meters import StopwatchMeter, TimeMeter
from fairseq.sequence_generator import SequenceGenerator,Cure_SequenceGenerator
from fairseq.sequence_scorer import SequenceScorer
from clearml import Task as clTask
from transformers import GPT2Tokenizer
#import matplotlib.pyplot as plt
#from torchvision.utils import save_image


def main(args):
    assert args.path is not None, '--path required for generation!'
    assert not args.sampling or args.nbest == args.beam, \
        '--sampling requires --nbest to be equal to --beam'
    assert args.replace_unk is None or args.raw_text, \
        '--replace-unk requires a raw text dataset (--raw-text)'

    if args.max_tokens is None and args.max_sentences is None:
        args.max_tokens = 12000
    """
    if args.clearml==True:
        trans_task=clTask.init(project_name="translation",task_name=args.taskname)
    """
    output_f=codecs.open(args.outputfile,"w",encoding='utf8')
    print(args)

    use_cuda = torch.cuda.is_available() and not args.cpu

    # Load dataset splits
    task = tasks.setup_task(args)
    task.load_dataset(args.gen_subset)
    print('| {} {} {} examples'.format(args.data, args.gen_subset, len(task.dataset(args.gen_subset))))

    # Set dictionaries
    src_dict = task.source_dictionary
    tgt_dict = task.target_dictionary

    # Load ensemble
    print('| loading model(s) from {}'.format(args.path))
    models, _ = utils.load_ensemble_for_inference(args.path.split(':'), task, model_arg_overrides=eval(args.model_overrides))

    # Optimize ensemble for generation
    for model in models:
        model.make_generation_fast_(
            beamable_mm_beam_size=None if args.no_beamable_mm else args.beam,
            need_attn=args.print_alignment,
        )
        if args.fp16:
            model.half()

    # Load alignment dictionary for unknown word replacement
    # (None if no unknown word replacement, empty if no path to align dictionary)
    align_dict = utils.load_align_dict(args.replace_unk)
    if args.task=="cure":
        max_positions=(1022,1022)
    else:
        max_positions = utils.resolve_max_positions(
            task.max_positions(),
            *[model.max_positions() for model in models]
        )
    # Load dataset (possibly sharded)
    itr = task.get_batch_iterator(
        dataset=task.dataset(args.gen_subset),
        max_tokens=args.max_tokens,
        max_sentences=args.max_sentences,
        max_positions=max_positions,
        ignore_invalid_inputs=args.skip_invalid_size_inputs_valid_test,
        required_batch_size_multiple=8,
        num_shards=args.num_shards,
        shard_id=args.shard_id,
    ).next_epoch_itr(shuffle=False)

    # Initialize generator
    gen_timer = StopwatchMeter()
    if args.task=="cure":
        tokenizer = GPT2Tokenizer.from_pretrained("microsoft/CodeGPT-small-java")
        tokenizer.add_tokens(["CaMeL", "$NUMBER", "$STRING$"])
    if args.score_reference:
        translator = SequenceScorer(models, task.target_dictionary)
    else:
        if args.task=="cure":
            translator=Cure_SequenceGenerator(
                models, tokenizer, beam_size=args.beam, minlen=args.min_len,
                stop_early=(not args.no_early_stop), normalize_scores=(not args.unnormalized),
                len_penalty=args.lenpen, unk_penalty=args.unkpen,
                sampling=args.sampling, sampling_topk=args.sampling_topk,
                sampling_temperature=args.sampling_temperature,
                diverse_beam_groups=args.diverse_beam_groups, diverse_beam_strength=args.diverse_beam_strength,
            )
        else:
            translator = SequenceGenerator(
                models, task.target_dictionary, beam_size=args.beam, minlen=args.min_len,
                stop_early=(not args.no_early_stop), normalize_scores=(not args.unnormalized),
                len_penalty=args.lenpen, unk_penalty=args.unkpen,
                sampling=args.sampling, sampling_topk=args.sampling_topk, sampling_temperature=args.sampling_temperature,
                diverse_beam_groups=args.diverse_beam_groups, diverse_beam_strength=args.diverse_beam_strength,
            )

    if use_cuda:
        translator.cuda()

    # Generate and compute BLEU score
    if args.task=="cure":
        scorer = bleu.Scorer(tokenizer.pad_token_id, tokenizer.eos_token_id, tokenizer.unk_token_id)
    else:
        scorer = bleu.Scorer(tgt_dict.pad(), tgt_dict.eos(), tgt_dict.unk())
    num_sentences = 0
    has_target = True

    translations_result=[]
    with progress_bar.build_progress_bar(args, itr) as t:
        if args.score_reference:
            translations = translator.score_batched_itr(t, cuda=use_cuda, timer=gen_timer)
        else:
            translations = translator.generate_batched_itr(
                t, maxlen_a=args.max_len_a, maxlen_b=args.max_len_b,
                cuda=use_cuda, timer=gen_timer, prefix_size=args.prefix_size,
            )

        wps_meter = TimeMeter()
        for sample_id, src_tokens, target_tokens, hypos in translations:
            # Process input and ground truth
            has_target = target_tokens is not None
            target_tokens = target_tokens.int().cpu() if has_target else None

            # Either retrieve the original sentences or regenerate them from tokens.
            if align_dict is not None:
                src_str = task.dataset(args.gen_subset).src.get_original_text(sample_id)
                target_str = task.dataset(args.gen_subset).tgt.get_original_text(sample_id)
            else:
                if args.task=="cure":
                    def bpe_string( tensor, tokenizer,bpe_symbol=None, escape_unk=False):
                        if torch.is_tensor(tensor) and tensor.dim() == 2:
                            return '\n'.join(bpe_string(t) for t in tensor)

                        sent = tokenizer.decode(tensor)
                        print(sent)
                        if bpe_symbol is not None:
                            sent = (sent + ' ').replace(bpe_symbol, '').rstrip()
                        return sent
                    src_str = bpe_string(src_tokens,tokenizer, args.remove_bpe)
                    if has_target:
                        target_str = bpe_string(target_tokens,tokenizer, args.remove_bpe, escape_unk=True)
                else:
                    src_str = src_dict.string(src_tokens, args.remove_bpe)
                    if has_target:
                        target_str = tgt_dict.string(target_tokens, args.remove_bpe, escape_unk=True)
            if not args.quiet:
                print('S-{}\t{}'.format(sample_id, src_str),file=output_f)
                if has_target:
                    print('T-{}\t{}'.format(sample_id, target_str),file=output_f)

            # Process top predictions
            for i, hypo in enumerate(hypos[:min(len(hypos), args.nbest)]):
                if args.task=="cure":
                    hypo_tokens, hypo_str, alignment = utils.post_process_prediction_bpe(
                        hypo_tokens=hypo['tokens'].int().cpu(),
                        tokenizer=tokenizer,
                        alignment=hypo['alignment'].int().cpu() if hypo['alignment'] is not None else None,

                        remove_bpe=args.remove_bpe,
                    )
                else:
                    hypo_tokens, hypo_str, alignment = utils.post_process_prediction(
                        hypo_tokens=hypo['tokens'].int().cpu(),
                        src_str=src_str,
                        alignment=hypo['alignment'].int().cpu() if hypo['alignment'] is not None else None,
                        align_dict=align_dict,
                        tgt_dict=tgt_dict,
                        remove_bpe=args.remove_bpe,
                    )

                if not args.quiet:
                    print('H-{}\t{}\t{}'.format(sample_id, hypo['score'], hypo_str),file=output_f)
                    """
                    print('P-{}\t{}'.format(
                        sample_id,
                        ' '.join(map(
                            lambda x: '{:.4f}'.format(x),
                            hypo['positional_scores'].tolist(),
                        ))
                    ),file=output_f)
                    
                    if args.print_alignment:
                        try:
                            if hypo_str == target_str:
                                fig, ax = plt.subplots()
                                attention = hypo['attention']
                                n_attention = attention.cpu().numpy()
                                print("n_attention:")
                                print(n_attention)
                                #x_values = ['']
                                x_values = hypo_str.split(' ')
                                y_values = src_str.split(' ')
                                x_values.append('EOS')
                                y_values.append('EOS')
                                try:
                                    assert(len(y_values) == attention.size()[0])
                                    assert(len(x_values) == attention.size()[1])
                                except:
                                    print("ERROR")
                                    print(y_values)
                                    print(x_values)
                                    print(attention.size()[0])
                                    print(attention.size()[1])
                                    plt.close(fig)
                                    continue
                                #plt.yticks(y_values)
                                #plt.xticks(x_values)
                                rows = np.array(y_values, dtype='|S50')[:, np.newaxis]

                                #with open('/local/tlutelli/heat-{}-{}.csv'.format(sample_id,i)) as f:
                                #    np.savetxt(f, np.hstack((rows, n_attention)), delimiter=",", header=x_values)

                                im = ax.imshow(n_attention, cmap='coolwarm', interpolation='nearest')
                                ax.set_xticks(np.arange(len(x_values)))
                                ax.set_yticks(np.arange(len(y_values)))
                                ax.xaxis.set_tick_params(labelsize=3)
                                ax.yaxis.set_tick_params(labelsize=3)
                                ax.set_xticklabels(x_values)
                                ax.set_yticklabels(y_values)
                                ax.xaxis.tick_top()
                                ax.set_xlabel('Generated fix')
                                ax.xaxis.set_label_position('top')
                                ax.set_ylabel("Input buggy line")
                                #plt.xlabel("Generated fix")
                                #plt.ylabel("Input buggy line")
                                plt.colorbar(im)
                                plt.setp(ax.get_xticklabels(), rotation=45, ha="left", rotation_mode="anchor")
                                if len(x_values) <= 15 and len(y_values) <= 15:
                                    for y in range(len(y_values)):
                                        for x in range(len(x_values)):
                                            if n_attention[y,x].round(decimals=1) != 0.0:
                                                text = ax.text(x,y, n_attention[y, x].round(decimals=1), ha="center", va="center")# color="b")
                                fig.tight_layout()
                                plt.savefig('/local/tlutelli/attention_maps/context_heat-{}-{}.png'.format(sample_id,i), dpi=900)
                                plt.close(fig)
                                print('ATTENTION-{}\t{}'.format(sample_id, n_attention))
                        except:
                            continue
                        print('A-{}\t{}'.format(sample_id, ' '.join(map(lambda x: str(utils.item(x)), alignment))))
                    """
                # Score only the top hypothesis
                if has_target and i == 0:
                    if align_dict is not None or args.remove_bpe is not None:
                        # Convert back to tokens for evaluation with unk replacement and/or without BPE
                        target_tokens = tokenizer.Tokenizer.tokenize(
                            target_str, tgt_dict, add_if_not_exist=True)
                    #print("target_tokens",target_tokens)
                    #print("hypo_tokens",hypo_tokens)
                    scorer.add(target_tokens, hypo_tokens)

            wps_meter.update(src_tokens.size(0))
            t.log({'wps': round(wps_meter.avg)})
            num_sentences += 1

    print('| Translated {} sentences ({} tokens) in {:.1f}s ({:.2f} sentences/s, {:.2f} tokens/s)'.format(
        num_sentences, gen_timer.n, gen_timer.sum, num_sentences / gen_timer.sum, 1. / gen_timer.avg))
    if has_target:
        print('| Generate {} with beam={}: {}'.format(args.gen_subset, args.beam, scorer.result_string()))
        #print('| Generate {} with beam={}: {}'.format(args.gen_subset, args.beam))
def load_dicts(args):
    assert args.path is not None, '--path required for generation!'
    assert not args.sampling or args.nbest == args.beam, \
        '--sampling requires --nbest to be equal to --beam'
    assert args.replace_unk is None or args.raw_text, \
        '--replace-unk requires a raw text dataset (--raw-text)'

    if args.max_tokens is None and args.max_sentences is None:
        args.max_tokens = 12000
    """
    if args.clearml==True:
        trans_task=clTask.init(project_name="translation",task_name=args.taskname)
    """
    output_f=codecs.open(args.outputfile,"w",encoding='utf8')
    print(args)

    use_cuda = torch.cuda.is_available() and not args.cpu

    # Load dataset splits
    task = tasks.setup_task(args)
    #task.load_dataset(args.gen_subset)
    #print('| {} {} {} examples'.format(args.data, args.gen_subset, len(task.dataset(args.gen_subset))))

    # Set dictionaries
    src_dict = task.source_dictionary
    tgt_dict = task.target_dictionary

    # Load ensemble
    print('| loading model(s) from {}'.format(args.path))
    models, _ = utils.load_ensemble_for_inference(args.path.split(':'), task, model_arg_overrides=eval(args.model_overrides))
    print(src_dict)
    print(tgt_dict)


if __name__ == '__main__':
    parser = options.get_generation_parser()
    parser.add_argument("-clearml",default=False,type=bool)
    parser.add_argument("-taskname",default=None)
    parser.add_argument("-outputfile",default='pred.txt')
    args = options.parse_args_and_arch(parser)
    main(args)
    #load_dicts(args)
