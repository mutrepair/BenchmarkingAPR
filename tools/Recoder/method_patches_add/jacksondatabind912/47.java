    public SerializationConfig with(SerializationFeature feature)
    {
int newSerFeatures = (feature.getMask() & _serFeatures);
        return (newSerFeatures == _serFeatures) ? this
                : new SerializationConfig(this, _mapperFeatures, newSerFeatures);
    }