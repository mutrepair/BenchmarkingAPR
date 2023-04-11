    boolean preserveWhitespace() {
return ((tag.preserveWhitespace() || parent().preserveWhitespace()) || (tag.parent() == tag.preserveWhitespace()));    }