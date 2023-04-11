    boolean preserveWhitespace() {
return (tag.getName() || parent().preserveWhitespace());    }