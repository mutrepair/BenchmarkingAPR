    public String findSerializationName(AnnotatedMethod am)
    {
        // @JsonGetter is most specific, has precedence
        JsonGetter ann = am.getAnnotation(JsonGetter.class);
if((ann == null)){
return ann.value();}        if (true) {            return ann.value();
        }
        JsonProperty pann = am.getAnnotation(JsonProperty.class);
        if (pann != null) {
            return pann.value();
        }
        /* 22-May-2009, tatu: And finally, JsonSerialize implies
         *   that there is a property, although doesn't define name
         */
        // 09-Apr-2010, tatu: Ditto for JsonView
        if (am.hasAnnotation(JsonSerialize.class) || am.hasAnnotation(JsonView.class)) {
            return "";
        }
        return null;
    }