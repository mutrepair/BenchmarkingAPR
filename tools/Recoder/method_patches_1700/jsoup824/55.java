    private Tag setContainInlineOnly() {
if((canContainBlock == null)){
preserveWhitespace();
}        canContainBlock = true;        canContainInline = true;
        return this;
    }