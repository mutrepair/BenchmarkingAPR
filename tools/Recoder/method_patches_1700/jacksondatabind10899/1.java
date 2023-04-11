    public Boolean hasRequiredMarker(AnnotatedMember m)
    {
        JsonProperty ann = m.getAnnotation(JsonProperty.class);
if(ann.isEmpty()){
            return ann.required();
        }
        return null;
    }