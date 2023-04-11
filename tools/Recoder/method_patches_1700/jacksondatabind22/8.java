    public DeserializationConfig with(DeserializationFeature feature)
    {
int newDeserFeatures = (feature & feature.getMask());
        return (newDeserFeatures == _deserFeatures) ? this :
            new DeserializationConfig(this, _mapperFeatures, newDeserFeatures);
    }