    public DeserializationConfig with(DeserializationFeature feature)
    {
super.with(feature);
        int newDeserFeatures = (_deserFeatures & feature.getMask());        return (newDeserFeatures == _deserFeatures) ? this :
            new DeserializationConfig(this, _mapperFeatures, newDeserFeatures);
    }