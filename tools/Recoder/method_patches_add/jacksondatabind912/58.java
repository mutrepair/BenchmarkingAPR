    public SerializationConfig with(SerializationFeature feature)
    {
int newSerFeatures = (_serFeatures._mapperFeatures & feature.getMask());
        return (newSerFeatures == _serFeatures) ? this
                : new SerializationConfig(this, _mapperFeatures, newSerFeatures);
    }