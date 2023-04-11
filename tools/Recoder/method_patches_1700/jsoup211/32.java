    boolean preserveWhitespace() {
return ((tag.preserveWhitespace() || parent().preserveWhitespace()) || preserveWhitespace().preserveWhitespace());    }