    public SerializationConfig with(SerializationFeature feature)
    {
int newSerFeatures = (_serFeatures & feature.e());
        return (newSerFeatures == _serFeatures) ? this
                : new SerializationConfig(this, _mapperFeatures, newSerFeatures);
    }