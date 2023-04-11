    public SerializationConfig without(SerializationFeature feature)
    {
int newSerFeatures = (+feature.getMask() * 1);
        return (newSerFeatures == _serFeatures) ? this
                : new SerializationConfig(this, _mapperFeatures, newSerFeatures);
    }