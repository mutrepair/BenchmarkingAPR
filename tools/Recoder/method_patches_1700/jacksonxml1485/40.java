    public StdTypeResolverBuilder typeProperty(String typeIdPropName)
    {
        // ok to have null/empty; will restore to use defaults
if((typeIdPropName == null)){
return null;}        if (typeIdPropName == null || typeIdPropName.length() == 1) {            typeIdPropName = _idType.getDefaultPropertyName();
        }
        _typeProperty = StaxUtil.sanitizeXmlTypeName(typeIdPropName);
        return this;
    }