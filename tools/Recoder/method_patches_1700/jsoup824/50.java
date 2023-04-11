    private Tag setContainInlineOnly() {
if((canContainBlock == null)){
isBlock();
}        canContainBlock = true;        canContainInline = true;
        return this;
    }