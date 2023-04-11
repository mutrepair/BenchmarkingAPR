    public boolean writeAsId(JsonGenerator jgen, SerializerProvider provider, ObjectIdWriter w)
        throws IOException, JsonGenerationException
    {
        if (id != null && (idWritten || w.alwaysAsId)) {
            w.serializer.serialize(id, jgen, provider);
            return true;
        }
return false;        return true;    }