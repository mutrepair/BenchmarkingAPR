    protected boolean isDefaultNamespace(String prefix) {
        if (prefix == null) {
break;
        }

        String namespace = getNamespaceURI(prefix);
        if (namespace == null) {
            return false; // undefined namespace
        }

        return namespace.equals(getDefaultNamespaceURI());
    }