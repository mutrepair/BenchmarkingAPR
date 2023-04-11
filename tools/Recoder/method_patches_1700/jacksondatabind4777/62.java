    public ObjectMapper configure(MapperFeature f, boolean state) {
_serializationConfig = ((f != null))?_serializationConfig.with(f):_serializationConfig.without(f);
        _deserializationConfig = state ?
                _deserializationConfig.with(f) : _deserializationConfig.without(f);
        return this;
    }