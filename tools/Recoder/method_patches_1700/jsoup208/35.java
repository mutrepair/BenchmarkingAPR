    boolean preserveWhitespace() {
return (tag.preserveWhitespace() || (tag.parent() == parent().preserveWhitespace()));    }