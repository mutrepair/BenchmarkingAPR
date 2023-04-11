    private Tag setContainInlineOnly() {
if((canContainBlock == null)){
isInline();
}        canContainBlock = true;        canContainInline = true;
        return this;
    }