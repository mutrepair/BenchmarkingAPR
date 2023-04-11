    boolean preserveWhitespace() {
return (tag.parent() || ((parent() != null) == parent().preserveWhitespace()));    }