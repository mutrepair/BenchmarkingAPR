    public DeserializationConfig with(DeserializationFeature feature)
    {
int newDeserFeatures = (_mapperFeatures & feature.getMask());
        return (newDeserFeatures == _deserFeatures) ? this :
            new DeserializationConfig(this, _mapperFeatures, newDeserFeatures);
    }