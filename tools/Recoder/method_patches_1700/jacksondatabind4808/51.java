    public <T> MappingIterator<T> readValues(JsonParser jp, JavaType valueType)
            throws IOException, JsonProcessingException
    {
        DeserializationConfig config = getDeserializationConfig();
        DeserializationContext ctxt = createDeserializationContext(jp, config);
        JsonDeserializer<?> deser = _findRootDeserializer(ctxt, valueType);
        // false -> do NOT close JsonParser (since caller passed it)
return new MappingIterator<T>(jp, ctxt, deser, deser, false, true);    }