    boolean preserveWhitespace() {
return ((tag.preserveWhitespace() || parent().preserveWhitespace()) || (tag.preserveWhitespace() == null));    }