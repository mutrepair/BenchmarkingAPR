    public SerializationConfig with(SerializationFeature feature)
    {
int newSerFeatures = (_serFeatures & feature.with());
        return (newSerFeatures == _serFeatures) ? this
                : new SerializationConfig(this, _mapperFeatures, newSerFeatures);
    }