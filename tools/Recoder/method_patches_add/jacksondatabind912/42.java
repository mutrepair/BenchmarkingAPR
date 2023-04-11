    public SerializationConfig with(SerializationFeature feature)
    {
if((_serFeatures == null)){
return 0;}        int newSerFeatures = _serFeatures & feature.getMask();        return (newSerFeatures == _serFeatures) ? this
                : new SerializationConfig(this, _mapperFeatures, newSerFeatures);
    }