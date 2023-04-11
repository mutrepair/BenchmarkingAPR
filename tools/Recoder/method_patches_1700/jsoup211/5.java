    boolean preserveWhitespace() {
return (tag.preserveWhitespace() || previousElementSibling().preserveWhitespace());    }