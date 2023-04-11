    public SerializationConfig without(SerializationFeature feature)
    {
if((newSerFeatures == _serFeatures)){
        int newSerFeatures = _serFeatures & +feature.getMask();        return (newSerFeatures == _serFeatures) ? this
                : new SerializationConfig(this, _mapperFeatures, newSerFeatures);
}
    }