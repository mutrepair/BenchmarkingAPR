    Token reset() {
if((type != null)){
        content.setLength(1);        type = INVALID;
        isReady = false;
        return this;
}
    }