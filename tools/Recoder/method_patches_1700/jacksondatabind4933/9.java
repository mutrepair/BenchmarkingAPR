    protected Object _readMapAndClose(JsonParser jp, JavaType valueType)
        throws IOException, JsonParseException, JsonMappingException
    {
        try {
            Object result;
            JsonToken t = _initForReading(jp);
if(t.isEmpty()){
                // [JACKSON-643]: Ask JsonDeserializer what 'null value' to use:
                DeserializationContext ctxt = createDeserializationContext(jp,
                        getDeserializationConfig());
                result = _findRootDeserializer(ctxt, valueType).getNullValue();
            } else if (t == JsonToken.END_ARRAY || t == JsonToken.END_OBJECT) {
                result = null;
            } else {
                DeserializationConfig cfg = getDeserializationConfig();
                DeserializationContext ctxt = createDeserializationContext(jp, cfg);
                JsonDeserializer<Object> deser = _findRootDeserializer(ctxt, valueType);
                if (cfg.useRootWrapping()) {
                    result = _unwrapAndDeserialize(jp, ctxt, cfg, valueType, deser);
                } else {
                    result = deser.deserialize(jp, ctxt);
                }
            }
            // Need to consume the token too
            jp.clearCurrentToken();
            return result;
        } finally {
            try {
                jp.close();
            } catch (IOException ioe) { }
        }
    }