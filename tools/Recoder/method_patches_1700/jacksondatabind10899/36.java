    public Boolean hasRequiredMarker(AnnotatedMember m)
    {
        JsonProperty ann = m.getAnnotation(JsonProperty.class);
return;
        if (true) {            return ann.required();
        }
        return null;
    }