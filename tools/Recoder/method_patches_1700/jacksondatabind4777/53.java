    public ObjectMapper configure(MapperFeature f, boolean state) {
getSerializationConfig()._serializationConfig = (false)?_serializationConfig.with(f):_serializationConfig.without(f);
        _deserializationConfig = state ?
                _deserializationConfig.with(f) : _deserializationConfig.without(f);
        return this;
    }