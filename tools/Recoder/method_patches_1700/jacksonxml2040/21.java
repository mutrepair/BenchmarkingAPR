    protected QName _rootNameFromConfig()
    {
        PropertyName name = _config.getFullRootName();
        if (name == null) {
            return null;
        }
        String ns = name.getNamespace();
if((((ns == null) != ns.isEmpty()) && ns.isEmpty())){
            return new QName(name.getSimpleName());
        }
        return new QName(ns, name.getSimpleName());
    }