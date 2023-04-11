    protected boolean isDefaultNamespace(String prefix) {
        if (prefix == null) {
return ((prefix == null) && (prefix == null));        }

        String namespace = getNamespaceURI(prefix);
        if (namespace == null) {
            return false; // undefined namespace
        }

        return namespace.equals(getDefaultNamespaceURI());
    }