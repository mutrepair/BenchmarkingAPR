# : ${2?"usage: ${0} <DATA_DIR> <MODEL_DIR>"}

# #Inputs
# DATA_DIR="${1}";
# MODEL_DIR="${2}";

# check the number of arguments and explain the usage of each argument
if [ $# -ne 6 ]; then
	echo "usage: ${0} <LD_LIBRARY_PATH> <VOCAB_SOURCE> <VOCAB_TARGET> <TEST_SOURCES> <MODEL_DIR> <PRED_DIR_PREFIX>"
	exit 1
fi


export CUDA_VISIBLE_DEVICES=0
export TF_FORCE_GPU_ALLOW_GROWTH=true
export LD_LIBRARY_PATH=$LD_LIBRARY_PATH:$1


# #Setting environment variables
# export DATA_DIR=${DATA_DIR}
export VOCAB_SOURCE=$2
export VOCAB_TARGET=$3
export TEST_SOURCES=$4
# export TEST_TARGETS=${DATA_DIR}/test/fixed.txt
export MODEL_DIR=$5

export PRED_DIR_PREFIX=${MODEL_DIR}/$6

PRED_SCRIPTS="predictions/"


echo "------------------- TESTING BEAM SEARCH ------------------------" 

beam_widths=("100")

for beam_width in ${beam_widths[*]}; do
	
	echo "Beam width: $beam_width"
	
	export PRED_DIR=${PRED_DIR_PREFIX}$beam_width
	mkdir -p ${PRED_DIR}
	
	SECONDS=0;
	
python3 -m bin.infer \
  --tasks "
    - class: DecodeText
    - class: DumpBeams
      params:
        file: ${PRED_DIR}/beams.npz" \
  --model_dir $MODEL_DIR \
  --model_params "
    inference.beam_search.beam_width: $beam_width" \
  --input_pipeline "
    class: ParallelTextInputPipeline
    params:
      source_files:
        - $TEST_SOURCES" \
  > ${PRED_DIR}/predictions.beam.txt

	elapsed=$SECONDS;
	echo "---------- TIME REPORT ----------" 
	echo "Beam width: $beam_width"
	echo "Total seconds: $elapsed"

	# total=`wc -l ${TEST_TARGETS}| awk '{print $1}'`
	# patches=$(($total * $beam_width))
	# avg="$(echo "scale=6; $elapsed / $patches" | bc)"
	# avg_bug="$(echo "scale=6; $elapsed / $total" | bc)"
	
	# echo "Total bugs: $total"
	# echo "Total patches: $patches" 
	# echo "Avg patch/sec: $avg"
	# echo "Avg bug/sec: $avg_bug"
	echo "---------------------------------"
	


# #Run prediction classification
# total=`wc -l ${TEST_TARGETS}| awk '{print $1}'`
# echo "Test Set: $total"
cp $VOCAB_TARGET ${PRED_DIR}/
python3 $PRED_SCRIPTS/beamPredictions.py ${PRED_DIR}
# output=$(python3 $PRED_SCRIPTS/beam_prediction_classifier.py ${TEST_SOURCES} ${TEST_TARGETS} "${PRED_DIR}/predictions.beam.mul.txt" 2>&1)
# perf=`awk '{print $1}' <<< "$output"`
# changed=`awk '{print $2}' <<< "$output"`
# all_bad=`awk '{print $3}' <<< "$output"`
# avg=`awk '{print $4}' <<< "$output"`
# bad=`awk '{print $5}' <<< "$output"`
# perf_perc="$(echo "scale=2; $perf * 100 / $total" | bc)"

# echo "---------- PREDICTIONS REPORT ----------"
# echo "Beam width: $beam_width"
# echo "Perf: $perf ($perf_perc%)"
# echo "Pot : $changed"
# echo "Bad : $all_bad"
# echo "Avg_Pot : $avg"
# echo "Min Bad : $bad" 
# echo "--------------------------------------"	
	
done

