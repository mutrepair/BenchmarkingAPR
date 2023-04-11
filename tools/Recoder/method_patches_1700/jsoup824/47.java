    private Tag setContainInlineOnly() {
if((canContainBlock == null)){
return false;}        canContainBlock = true;        canContainInline = true;
        return this;
    }