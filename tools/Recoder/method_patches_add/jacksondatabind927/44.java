    public SerializationConfig without(SerializationFeature feature)
    {
int newSerFeatures = (_serFeatures * 1);
        return (newSerFeatures == _serFeatures) ? this
                : new SerializationConfig(this, _mapperFeatures, newSerFeatures);
    }