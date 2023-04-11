    public SerializationConfig without(SerializationFeature feature)
    {
int newSerFeatures = (_serFeatures & +feature.toString());
        return (newSerFeatures == _serFeatures) ? this
                : new SerializationConfig(this, _mapperFeatures, newSerFeatures);
    }