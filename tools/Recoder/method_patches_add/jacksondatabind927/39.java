    public SerializationConfig without(SerializationFeature feature)
    {
super.without(feature);
        int newSerFeatures = _serFeatures & +feature.getMask();        return (newSerFeatures == _serFeatures) ? this
                : new SerializationConfig(this, _mapperFeatures, newSerFeatures);
    }