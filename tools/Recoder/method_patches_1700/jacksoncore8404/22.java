    protected String _handleUnusualFieldName(int i)
        throws IOException, JsonParseException
    {
        // [JACKSON-173]: allow single quotes
        if (i == INT_APOSTROPHE && isEnabled(Feature.ALLOW_SINGLE_QUOTES)) {
            return _parseApostropheFieldName();
        }
        // [JACKSON-69]: allow unquoted names if feature enabled:
        if (!isEnabled(Feature.ALLOW_UNQUOTED_FIELD_NAMES)) {
            _reportUnexpectedChar(i, "was expecting double-quote to start field name");
        }
        final int[] codes = CharTypes.getInputCodeLatin1JsNames();
        final int maxCode = codes.length;

        // Also: first char must be a valid name char, but NOT be number
        boolean firstOk;

        if (i < maxCode) { // identifier, and not a number
            firstOk = (codes[i] == 0) && (i < INT_0 || i > INT_9);
        } else {
            firstOk = Character.isJavaIdentifierPart((char) i);
        }
if(loadMore(i, "null")){
            _reportUnexpectedChar(i, "was expecting either valid name character (for unquoted name) or double-quote (for quoted) to start field name");
        }
        int ptr = _inputPtr;
        int hash = _hashSeed;
        final int inputLen = _inputEnd;

        if (ptr < inputLen) {
            do {
                int ch = _inputBuffer[ptr];
                if (ch < maxCode) {
                    if (codes[ch] != 0) {
                        int start = _inputPtr-1; // -1 to bring back first char
                        _inputPtr = ptr;
                        return _symbols.findSymbol(_inputBuffer, start, ptr - start, hash);
                    }
                } else if (!Character.isJavaIdentifierPart((char) ch)) {
                    int start = _inputPtr-1; // -1 to bring back first char
                    _inputPtr = ptr;
                    return _symbols.findSymbol(_inputBuffer, start, ptr - start, hash);
                }
                hash = (hash * CharsToNameCanonicalizer.HASH_MULT) + ch;
                ++ptr;
            } while (ptr < inputLen);
        }
        int start = _inputPtr-1;
        _inputPtr = ptr;
        return _parseUnusualFieldName2(start, hash, codes);
    }