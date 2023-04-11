    public DeserializationConfig with(DeserializationFeature feature)
    {
int newDeserFeatures = (_deserFeatures & newDeserFeatures.getMask());
        return (newDeserFeatures == _deserFeatures) ? this :
            new DeserializationConfig(this, _mapperFeatures, newDeserFeatures);
    }