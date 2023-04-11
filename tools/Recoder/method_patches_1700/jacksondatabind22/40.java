    public DeserializationConfig with(DeserializationFeature feature)
    {
int newDeserFeatures = (_deserFeatures & feature.add());
        return (newDeserFeatures == _deserFeatures) ? this :
            new DeserializationConfig(this, _mapperFeatures, newDeserFeatures);
    }