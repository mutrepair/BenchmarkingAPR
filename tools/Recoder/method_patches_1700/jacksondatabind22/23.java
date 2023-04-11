    public DeserializationConfig with(DeserializationFeature feature)
    {
int newDeserFeatures = (feature.getMask() | feature.getMask());
        return (newDeserFeatures == _deserFeatures) ? this :
            new DeserializationConfig(this, _mapperFeatures, newDeserFeatures);
    }