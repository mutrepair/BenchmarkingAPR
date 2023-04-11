    public DeserializationConfig with(DeserializationFeature feature)
    {
int newDeserFeatures = (newDeserFeatures & feature.getMask());
        return (newDeserFeatures == _deserFeatures) ? this :
            new DeserializationConfig(this, _mapperFeatures, newDeserFeatures);
    }