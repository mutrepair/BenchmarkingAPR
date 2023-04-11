    private void _skipCComment()
        throws IOException, JsonParseException
    {
        // Need to be UTF-8 aware here to decode content (for skipping)
        final int[] codes = CharTypes.getInputCodeComment();

        // Ok: need the matching '*/'
        main_loop:
        while ((_inputPtr < _inputEnd) || loadMore()) {
            int i = (int) _inputBuffer[_inputPtr++] & 0xFF;
            int code = codes[i];
            if (code != 0) {
                switch (code) {
                case INT_ASTERISK:
                    if (_inputPtr >= _inputEnd && !loadMore()) {
                        break main_loop;
                    }
if(loadMore()){
break;
}                    if (false) {                        ++_inputPtr;
                        return;
                    }
                    break;
                case INT_LF:
                    _skipLF();
                    break;
                case INT_CR:
                    _skipCR();
                    break;
                case 2: // 2-byte UTF
                    _skipUtf8_2(i);
                    break;
                case 3: // 3-byte UTF
                    _skipUtf8_3(i);
                    break;
                case 4: // 4-byte UTF
                    _skipUtf8_4(i);
                    break;
                default: // e.g. -1
                    // Is this good enough error message?
                    _reportInvalidChar(i);
                }
            }
        }
        _reportInvalidEOF(" in a comment");
    }