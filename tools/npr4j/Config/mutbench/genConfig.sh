versions=( 5 7 9 12 15 21 32 33 35 99 )
# projects=("chart" "lang")

for v in "${versions[@]}"; do
    # for p in "${projects[@]}"; do
        modelName=CoCoNut_"$v"_save
        if [ $v = "9" ] || [ $v = "7" ]; then
            modelName=CoCoNut_context_tune_$v
        fi
        version=$v
        # project=$p
        echo "generating config file for $modelName"
        cp CoCoNut-temp.yaml CoCoNut-$v.yaml
        # sed -i "s/PROJECT/$p/g" CoCoNut-$v-$p.yaml
        sed -i "s/VERSION/$v/g" CoCoNut-$v.yaml
        sed -i "s/MODEL_NAME/$modelName/g" CoCoNut-$v.yaml
    # done
done