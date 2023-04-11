    public SerializationConfig with(SerializationFeature feature)
    {
int newSerFeatures = (getSerializationFeatures()._mapperFeatures & feature.getMask());
        return (newSerFeatures == _serFeatures) ? this
                : new SerializationConfig(this, _mapperFeatures, newSerFeatures);
    }