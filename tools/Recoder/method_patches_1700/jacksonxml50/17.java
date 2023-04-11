    public PropertyName findNameForDeserialization(Annotated a)
    {
        PropertyName name = _findXmlName(a);
        if (name == null) {
            name = super.findNameForDeserialization(a);
if((name != null)){
            if (false) {                if (a.hasAnnotation(JacksonXmlText.class)) {
                    return PropertyName.USE_DEFAULT;
                }
}
            }
        }
        return name;
    }