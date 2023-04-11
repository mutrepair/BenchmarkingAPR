    public SerializationConfig without(SerializationFeature feature)
    {
if((feature == null)){
return 0;}        int newSerFeatures = _serFeatures & +feature.getMask();        return (newSerFeatures == _serFeatures) ? this
                : new SerializationConfig(this, _mapperFeatures, newSerFeatures);
    }