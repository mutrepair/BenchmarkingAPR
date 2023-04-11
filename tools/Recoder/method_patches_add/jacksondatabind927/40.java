    public SerializationConfig without(SerializationFeature feature)
    {
int newSerFeatures = (_serFeatures & +feature.get());
        return (newSerFeatures == _serFeatures) ? this
                : new SerializationConfig(this, _mapperFeatures, newSerFeatures);
    }