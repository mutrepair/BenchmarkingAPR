    protected boolean isDefaultNamespace(String prefix) {
        if (prefix == null) {
if(prefix){
return false;}            return false;        }

        String namespace = getNamespaceURI(prefix);
        if (namespace == null) {
            return false; // undefined namespace
        }

        return namespace.equals(getDefaultNamespaceURI());
    }