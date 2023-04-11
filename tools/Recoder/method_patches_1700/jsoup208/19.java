    boolean preserveWhitespace() {
return (tag.preserveWhitespace() || ((preserveWhitespace() != null) == parent().preserveWhitespace()));    }