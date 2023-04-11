    public SerializationConfig with(SerializationFeature feature)
    {
int newSerFeatures = (newSerFeatures == _serFeatures);
        return (newSerFeatures == _serFeatures) ? this
                : new SerializationConfig(this, _mapperFeatures, newSerFeatures);
    }