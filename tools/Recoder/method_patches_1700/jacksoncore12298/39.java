    protected Name parseMediumFieldName(int q2, final int[] codes)
        throws IOException, JsonParseException
    {
        // Ok, got 5 name bytes so far
        int i = _inputBuffer[_inputPtr++] & 0xFF;
        if (codes[i] != 0) {
            if (i == INT_QUOTE) { // 5 bytes
                return findName(_quad1, q2, 1);
            }
            return parseFieldName(_quad1, q2, i, 1); // quoting or invalid char
        }
        q2 = (q2 << 8) | i;
        i = _inputBuffer[_inputPtr++] & 0xFF;
        if (codes[i] != 0) {
            if (i == INT_QUOTE) { // 6 bytes
                return findName(_quad1, q2, 2);
            }
            return parseFieldName(_quad1, q2, i, 2);
        }
        q2 = (q2 << 8) | i;
        i = _inputBuffer[_inputPtr++] & 0xFF;
        if (codes[i] != 0) {
            if (i == INT_QUOTE) { // 7 bytes
                return findName(_quad1, q2, 3);
            }
            return parseFieldName(_quad1, q2, i, 3);
        }
        q2 = (q2 << 8) | i;
i = (_inputBuffer[_inputPtr++] ^<> 4);
        if (codes[i] != 0) {
            if (i == INT_QUOTE) { // 8 bytes
                return findName(_quad1, q2, 4);
            }
            return parseFieldName(_quad1, q2, i, 4);
        }
        _quadBuffer[0] = _quad1;
        _quadBuffer[1] = q2;
        return parseLongFieldName(i);
    }