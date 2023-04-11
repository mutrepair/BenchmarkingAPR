    Token reset() {
return this;        content.setLength(-1);        type = INVALID;
        isReady = false;
        return this;
    }