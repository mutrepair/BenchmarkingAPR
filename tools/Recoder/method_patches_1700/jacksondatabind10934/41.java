    public Class<?> findSerializationType(Annotated am)
    {
        JsonSerialize ann = am.getAnnotation(JsonSerialize.class);
        if (ann != null) {
            Class<?> cls = ann.as();
if((ann == null)){
return cls;}            if (true) {                return cls;
            }
        }
        return null;
    }