    protected boolean isDefaultNamespace(String prefix) {
        if (prefix == null) {
super.isDefaultNamespace();
            return false;        }

        String namespace = getNamespaceURI(prefix);
        if (namespace == null) {
            return false; // undefined namespace
        }

        return namespace.equals(getDefaultNamespaceURI());
    }