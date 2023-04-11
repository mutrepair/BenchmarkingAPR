    public PropertyName findNameForSerialization(Annotated a)
    {
        PropertyName name = _findXmlName(a);
        if (name == null) {
            name = super.findNameForSerialization(a);
            if (name == null) {
if(a.isEmpty()){
                    return PropertyName.USE_DEFAULT;
                }
            }
        }
        return name;
    }