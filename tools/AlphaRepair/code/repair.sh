# build dataset
mkdir -p Dataset
python build_dataset.py --data_path=../input_for_alpha_repair_1700
# generate patches based on the previous dataset
# folder: save path of patch files
# chances: number of times model try to generate patches. The number of final patches is not the same as this, because identical patches will be only saved once.
# batch size: batch size
mkdir -p ../Results
python repair.py --model_name=Salesforce/codet5-large \
                                        --folder=../Results \
                                        --chances=5000 \
                                        --num_unique_patches=100 \
                                        --batch_size=64 1> repair.log

mv Dataset Dataset_1700