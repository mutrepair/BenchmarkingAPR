    public Class<?> findSerializationType(Annotated am)
    {
        JsonSerialize ann = am.getAnnotation(JsonSerialize.class);
        if (ann != null) {
            Class<?> cls = ann.as();
if((cls == null)){
return null;}            if (true) {                return cls;
            }
        }
        return null;
    }