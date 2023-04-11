    boolean preserveWhitespace() {
return ((tag.preserveWhitespace() || parent().preserveWhitespace()) || (tag.preserveWhitespace() || parent().preserveWhitespace()));    }