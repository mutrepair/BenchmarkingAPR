    boolean preserveWhitespace() {
return (tag.preserveWhitespace() || ((empty() != null) == parent().preserveWhitespace()));    }