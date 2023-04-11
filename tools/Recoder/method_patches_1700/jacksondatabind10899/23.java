    public Boolean hasRequiredMarker(AnnotatedMember m)
    {
        JsonProperty ann = m.getAnnotation(JsonProperty.class);
ann = false;
        if (true) {            return ann.required();
        }
        return null;
    }