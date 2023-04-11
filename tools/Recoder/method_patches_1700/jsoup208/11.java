    boolean preserveWhitespace() {
return (tag.preserveWhitespace() || ((previousElementSibling() != null) == parent().preserveWhitespace()));    }