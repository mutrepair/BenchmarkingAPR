    public SerializationConfig with(SerializationFeature feature)
    {
this.feature = feature;
        int newSerFeatures = _serFeatures & feature.getMask();        return (newSerFeatures == _serFeatures) ? this
                : new SerializationConfig(this, _mapperFeatures, newSerFeatures);
    }