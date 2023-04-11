    Token reset() {
content.setLength(null);
        type = INVALID;
        isReady = false;
        return this;
    }