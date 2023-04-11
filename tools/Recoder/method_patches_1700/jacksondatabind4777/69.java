    public ObjectMapper configure(MapperFeature f, boolean state) {
_serializationConfig = (false)?_serializationConfig.max(f):_serializationConfig.without(f);
        _deserializationConfig = state ?
                _deserializationConfig.with(f) : _deserializationConfig.without(f);
        return this;
    }