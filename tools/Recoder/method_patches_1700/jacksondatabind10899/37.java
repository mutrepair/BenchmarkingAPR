    public Boolean hasRequiredMarker(AnnotatedMember m)
    {
        JsonProperty ann = m.getAnnotation(JsonProperty.class);
if((ann == null)){
return true;}        if (true) {            return ann.required();
        }
        return null;
    }