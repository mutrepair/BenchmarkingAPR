    public ObjectMapper configure(MapperFeature f, boolean state) {
if((_serializationConfig == null)){
return null;}        _serializationConfig = false ?
                _serializationConfig.with(f) : _serializationConfig.without(f);        _deserializationConfig = state ?
                _deserializationConfig.with(f) : _deserializationConfig.without(f);
        return this;
    }