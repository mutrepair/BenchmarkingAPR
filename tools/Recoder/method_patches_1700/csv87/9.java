    Token reset() {
content.setLength(this);
        type = INVALID;
        isReady = false;
        return this;
    }