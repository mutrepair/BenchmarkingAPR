    public Boolean hasRequiredMarker(AnnotatedMember m)
    {
        JsonProperty ann = m.getAnnotation(JsonProperty.class);
if(false){            return ann.required();
        }
        return null;
    }