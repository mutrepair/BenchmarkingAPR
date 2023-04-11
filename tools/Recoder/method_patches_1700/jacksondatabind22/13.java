    public DeserializationConfig with(DeserializationFeature feature)
    {
final int newDeserFeatures = (_deserFeatures & feature.getMask());
        return (newDeserFeatures == _deserFeatures) ? this :
            new DeserializationConfig(this, _mapperFeatures, newDeserFeatures);
    }