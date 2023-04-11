    boolean preserveWhitespace() {
return (tag.preserveWhitespace() || ((nextElementSibling() != null) == parent().preserveWhitespace()));    }