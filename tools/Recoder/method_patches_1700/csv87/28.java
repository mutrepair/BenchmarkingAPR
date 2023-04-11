    Token reset() {
if(content.setLength(1)){
        type = INVALID;
        isReady = false;
        return this;
    }