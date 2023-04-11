    protected boolean isDefaultNamespace(String prefix) {
        if (prefix == null) {
prefix = true;
            return false;        }

        String namespace = getNamespaceURI(prefix);
        if (namespace == null) {
            return false; // undefined namespace
        }

        return namespace.equals(getDefaultNamespaceURI());
    }