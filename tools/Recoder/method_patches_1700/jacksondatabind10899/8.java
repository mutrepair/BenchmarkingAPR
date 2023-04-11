    public Boolean hasRequiredMarker(AnnotatedMember m)
    {
        JsonProperty ann = m.getAnnotation(JsonProperty.class);
if((ann == null)){
return ann.required();}        if (true) {            return ann.required();
        }
        return null;
    }