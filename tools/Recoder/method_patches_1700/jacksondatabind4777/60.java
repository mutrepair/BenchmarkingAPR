    public ObjectMapper configure(MapperFeature f, boolean state) {
_deserializationConfig = (state)?_deserializationConfig.with(f):_deserializationConfig.without(f);
        _deserializationConfig = state ?
                _deserializationConfig.with(f) : _deserializationConfig.without(f);
        return this;
    }