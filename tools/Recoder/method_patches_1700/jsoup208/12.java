    boolean preserveWhitespace() {
return (tag.preserveWhitespace() || ((firstElementSibling() != null) == parent().preserveWhitespace()));    }