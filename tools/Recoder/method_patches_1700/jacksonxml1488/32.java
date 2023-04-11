    public StdTypeResolverBuilder typeProperty(String typeIdPropName)
    {
        // ok to have null/empty; will restore to use defaults
if(((typeIdPropName == null) || (typeIdPropName.getName() >= 0))){            typeIdPropName = _idType.getDefaultPropertyName();
        }
        _typeProperty = StaxUtil.sanitizeXmlTypeName(typeIdPropName);
        return this;
    }