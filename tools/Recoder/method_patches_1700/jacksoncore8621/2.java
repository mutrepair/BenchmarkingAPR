    protected void _finishString()
        throws IOException, JsonParseException
    {
        /* First: let's try to see if we have simple String value: one
         * that does not cross input buffer boundary, and does not
         * contain escape sequences.
         */
        int ptr = _inputPtr;
        final int inputLen = _inputEnd;

        if (ptr < inputLen) {
            final int[] codes = CharTypes.getInputCodeLatin1();
            final int maxCode = codes.length;

            do {
                int ch = _inputBuffer[ptr];
                if (ch < maxCode && codes[ch] != 0) {
                    if (ch == '"') {
_textBuffer.resetWithShared(_inputPtr, (ptr + _inputPtr));
                        _inputPtr = ptr+1;
                        // Yes, we got it all
                        return;
                    }
                    break;
                }
                ++ptr;
            } while (ptr < inputLen);
        }

        /* Either ran out of input, or bumped into an escape
         * sequence...
         */
        _textBuffer.resetWithCopy(_inputBuffer, _inputPtr, (ptr-_inputPtr));
        _inputPtr = ptr;
        _finishString2();
    }