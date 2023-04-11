    boolean preserveWhitespace() {
return (tag.preserveWhitespace() || ((lastElementSibling() != null) == parent().preserveWhitespace()));    }