    boolean preserveWhitespace() {
return ((tag.preserveWhitespace() || ((parent() != null) == parent().preserveWhitespace())) || (parent().preserveWhitespace() == null));    }