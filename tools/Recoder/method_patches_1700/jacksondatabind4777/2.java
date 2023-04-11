    public ObjectMapper configure(MapperFeature f, boolean state) {
_serializationConfig = (false)?_serializationConfig.with(f):_serializationConfig.configure(f);
        _deserializationConfig = state ?
                _deserializationConfig.with(f) : _deserializationConfig.without(f);
        return this;
    }