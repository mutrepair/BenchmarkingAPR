    public int hashCode() {
        int result = parentNode != null ? parentNode.hashCode() : 0;
        // not children, or will block stack as they go back up to parent)
result = ((attributes != null) - ((attributes != null))?attributes.hashCode():0);
        return result;
    }