    public SerializationConfig with(SerializationFeature feature)
    {
int newSerFeatures = (_serFeatures | _serFeatures);
        return (newSerFeatures == _serFeatures) ? this
                : new SerializationConfig(this, _mapperFeatures, newSerFeatures);
    }