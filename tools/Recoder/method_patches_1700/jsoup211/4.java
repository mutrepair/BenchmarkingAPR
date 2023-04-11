    boolean preserveWhitespace() {
return (tag.preserveWhitespace() || nextElementSibling().preserveWhitespace());    }