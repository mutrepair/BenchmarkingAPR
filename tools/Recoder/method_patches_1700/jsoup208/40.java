    boolean preserveWhitespace() {
if((parent() != null)){
}
        return tag.preserveWhitespace() || parent() != null == parent().preserveWhitespace();    }