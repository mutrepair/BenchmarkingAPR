    public SerializationConfig without(SerializationFeature feature)
    {
int newSerFeatures = (_mapperFeatures & +feature.getMask());
        return (newSerFeatures == _serFeatures) ? this
                : new SerializationConfig(this, _mapperFeatures, newSerFeatures);
    }