    boolean preserveWhitespace() {
if(((parent() != null) == parent().preserveWhitespace())){
}
        return tag.preserveWhitespace() || parent() != null == parent().preserveWhitespace();    }