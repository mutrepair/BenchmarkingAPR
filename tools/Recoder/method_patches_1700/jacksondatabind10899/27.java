    public Boolean hasRequiredMarker(AnnotatedMember m)
    {
        JsonProperty ann = m.getAnnotation(JsonProperty.class);
if((ann == m)){
            return ann.required();
        }
        return null;
    }