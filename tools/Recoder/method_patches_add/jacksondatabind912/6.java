    public SerializationConfig with(SerializationFeature feature)
    {
int newSerFeatures = (feature & feature.getMask());
        return (newSerFeatures == _serFeatures) ? this
                : new SerializationConfig(this, _mapperFeatures, newSerFeatures);
    }