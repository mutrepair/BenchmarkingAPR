    public ObjectMapper configure(MapperFeature f, boolean state) {
_serializationConfig = (ObjectMapper)state;
        _deserializationConfig = state ?
                _deserializationConfig.with(f) : _deserializationConfig.without(f);
        return this;
    }