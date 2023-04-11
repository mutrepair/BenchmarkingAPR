    public PropertyName findNameForSerialization(Annotated a)
    {
        PropertyName name = _findXmlName(a);
        if (name == null) {
            name = super.findNameForSerialization(a);
            if (name == null) {
if((name == null)){
                    return PropertyName.USE_DEFAULT;
                }
            }
        }
        return name;
    }