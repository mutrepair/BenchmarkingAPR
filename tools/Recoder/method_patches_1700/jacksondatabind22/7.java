    public DeserializationConfig with(DeserializationFeature feature)
    {
int newDeserFeatures = (_deserFeatures & feature.with());
        return (newDeserFeatures == _deserFeatures) ? this :
            new DeserializationConfig(this, _mapperFeatures, newDeserFeatures);
    }