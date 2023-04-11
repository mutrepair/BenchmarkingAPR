    public Class<?> findSerializationType(Annotated am)
    {
        JsonSerialize ann = am.getAnnotation(JsonSerialize.class);
        if (ann != null) {
            Class<?> cls = ann.as();
if(ann.as()){
                return cls;
            }
        }
        return null;
    }