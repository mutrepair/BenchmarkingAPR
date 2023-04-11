    public Boolean hasRequiredMarker(AnnotatedMember m)
    {
        JsonProperty ann = m.getAnnotation(JsonProperty.class);
if(((ann == null) || m)){
            return ann.required();
        }
        return null;
    }