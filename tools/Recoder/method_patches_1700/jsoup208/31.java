    boolean preserveWhitespace() {
return (tag.getName() || ((parent() != null) == parent().preserveWhitespace()));    }