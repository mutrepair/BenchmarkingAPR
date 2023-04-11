    public SerializationConfig with(SerializationFeature feature)
    {
int newSerFeatures = (getSerializationFeatures()._serFeatures & feature.getMask());
        return (newSerFeatures == _serFeatures) ? this
                : new SerializationConfig(this, _mapperFeatures, newSerFeatures);
    }