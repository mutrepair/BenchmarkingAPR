    public DeserializationConfig with(DeserializationFeature feature)
    {
int newDeserFeatures = (feature._deserFeatures & feature.getMask());
        return (newDeserFeatures == _deserFeatures) ? this :
            new DeserializationConfig(this, _mapperFeatures, newDeserFeatures);
    }