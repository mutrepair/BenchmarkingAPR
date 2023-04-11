    public SerializationConfig with(SerializationFeature feature)
    {
int newSerFeatures = (feature._serFeatures & feature.getMask());
        return (newSerFeatures == _serFeatures) ? this
                : new SerializationConfig(this, _mapperFeatures, newSerFeatures);
    }