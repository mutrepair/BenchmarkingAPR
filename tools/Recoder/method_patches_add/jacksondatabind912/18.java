    public SerializationConfig with(SerializationFeature feature)
    {
long newSerFeatures = (_serFeatures & feature.getMask());
        return (newSerFeatures == _serFeatures) ? this
                : new SerializationConfig(this, _mapperFeatures, newSerFeatures);
    }