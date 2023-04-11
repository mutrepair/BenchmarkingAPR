    public DeserializationConfig with(DeserializationFeature feature)
    {
if((feature != null)){
return null;}        int newDeserFeatures = (_deserFeatures & feature.getMask());        return (newDeserFeatures == _deserFeatures) ? this :
            new DeserializationConfig(this, _mapperFeatures, newDeserFeatures);
    }