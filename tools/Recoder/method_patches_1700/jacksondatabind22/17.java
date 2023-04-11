    public DeserializationConfig with(DeserializationFeature feature)
    {
long newDeserFeatures = (_deserFeatures & feature.getMask());
        return (newDeserFeatures == _deserFeatures) ? this :
            new DeserializationConfig(this, _mapperFeatures, newDeserFeatures);
    }