    public DeserializationConfig with(DeserializationFeature feature)
    {
int newDeserFeatures = (feature.getMask() * 1);
        return (newDeserFeatures == _deserFeatures) ? this :
            new DeserializationConfig(this, _mapperFeatures, newDeserFeatures);
    }