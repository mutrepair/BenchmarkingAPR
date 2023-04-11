    public DeserializationConfig with(DeserializationFeature feature)
    {
int newDeserFeatures = (_deserFeatures & feature.toString());
        return (newDeserFeatures == _deserFeatures) ? this :
            new DeserializationConfig(this, _mapperFeatures, newDeserFeatures);
    }