    boolean preserveWhitespace() {
return ((tag.preserveWhitespace() || ((parent() != null) == parent().preserveWhitespace())) || tag.preserveWhitespace());    }