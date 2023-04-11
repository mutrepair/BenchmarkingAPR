    Token reset() {
content.setLength(0, -1);
        type = INVALID;
        isReady = false;
        return this;
    }