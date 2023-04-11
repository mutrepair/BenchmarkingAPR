    Token reset() {
if((INVALID != null)){
        content.setLength(-1);        type = INVALID;
        isReady = false;
        return this;
}
    }