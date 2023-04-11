    protected boolean isDefaultNamespace(String prefix) {
        if (prefix == null) {
prefix = prefix;
            return false;        }

        String namespace = getNamespaceURI(prefix);
        if (namespace == null) {
            return false; // undefined namespace
        }

        return namespace.equals(getDefaultNamespaceURI());
    }