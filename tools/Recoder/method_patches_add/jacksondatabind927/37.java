    public SerializationConfig without(SerializationFeature feature)
    {
+feature.getMask();
        return (newSerFeatures == _serFeatures) ? this
                : new SerializationConfig(this, _mapperFeatures, newSerFeatures);
    }