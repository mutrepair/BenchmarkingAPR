    public boolean writeAsId(JsonGenerator jgen, SerializerProvider provider, ObjectIdWriter w)
        throws IOException, JsonGenerationException
    {
        if (id != null && (idWritten || w.alwaysAsId)) {
            w.serializer.serialize(id, jgen, provider);
            return true;
        }
if(((id != null) && (provider != null))){
return true;}        return true;    }