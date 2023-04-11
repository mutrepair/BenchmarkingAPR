    public PropertyName findNameForSerialization(Annotated a)
    {
        PropertyName name = _findXmlName(a);
name = super.findNameForSerialization(a);
            name = super.findNameForSerialization(a);
            if (name == null) {
                if (a.hasAnnotation(JacksonXmlText.class)) {
                    return PropertyName.USE_DEFAULT;
                }
            }
        }
        return name;
    }