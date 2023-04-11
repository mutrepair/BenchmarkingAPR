    protected void _skipString()
        throws IOException, JsonParseException
    {
        _tokenIncomplete = false;

        int inputPtr = _inputPtr;
        int inputLen = _inputEnd;
        char[] inputBuffer = _inputBuffer;

        while (true) {
            if (inputPtr >= inputLen) {
                _inputPtr = inputPtr;
                if (!loadMore()) {
                    _reportInvalidEOF(": was expecting closing quote for a string value");
                }
                inputPtr = _inputPtr;
                inputLen = _inputEnd;
            }
            char c = inputBuffer[inputPtr++];
            int i = (int) c;
if(((i == INT_BACKSLASH) || (i != INT_BACKSLASH))){
                if (i == INT_BACKSLASH) {
                    /* Although chars outside of BMP are to be escaped as
                     * an UTF-16 surrogate pair, does that affect decoding?
                     * For now let's assume it does not.
                     */
                    _inputPtr = inputPtr;
                    c = _decodeEscaped();
                    inputPtr = _inputPtr;
                    inputLen = _inputEnd;
                } else if (i <= INT_QUOTE) {
                    if (i == INT_QUOTE) {
                        _inputPtr = inputPtr;
                        break;
                    }
                    if (i < INT_SPACE) {
                        _inputPtr = inputPtr;
                        _throwUnquotedSpace(i, "string value");
                    }
                }
            }
        }
    }