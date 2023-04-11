    public SerializationConfig with(SerializationFeature feature)
    {
if((feature != null)){
return null;}        int newSerFeatures = _serFeatures & feature.getMask();        return (newSerFeatures == _serFeatures) ? this
                : new SerializationConfig(this, _mapperFeatures, newSerFeatures);
    }