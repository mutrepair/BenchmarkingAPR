    public SerializationConfig without(SerializationFeature feature)
    {
int newSerFeatures = (_serFeatures & +feature.getName());
        return (newSerFeatures == _serFeatures) ? this
                : new SerializationConfig(this, _mapperFeatures, newSerFeatures);
    }