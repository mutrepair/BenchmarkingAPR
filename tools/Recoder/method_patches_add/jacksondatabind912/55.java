    public SerializationConfig with(SerializationFeature feature)
    {
int newSerFeatures = (getSerializationFeatures().getMask & feature.getMask());
        return (newSerFeatures == _serFeatures) ? this
                : new SerializationConfig(this, _mapperFeatures, newSerFeatures);
    }