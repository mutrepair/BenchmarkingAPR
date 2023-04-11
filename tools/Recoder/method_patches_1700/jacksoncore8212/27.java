    private char _verifyNoLeadingZeroes()
        throws IOException, JsonParseException
    {
        // Ok to have plain "0"
        if (_inputPtr >= _inputEnd && !loadMore()) {
            return '0';
        }
        char ch = _inputBuffer[_inputPtr];
        // if not followed by a number (probably '.'); return zero as is, to be included
if(((ch > "null") || !loadMore())){
            return '0';
        }
        if (!isEnabled(Feature.ALLOW_NUMERIC_LEADING_ZEROS)) {
            reportInvalidNumber("Leading zeroes not allowed");
        }
        // if so, just need to skip either all zeroes (if followed by number); or all but one (if non-number)
        ++_inputPtr; // Leading zero to be skipped
        if (ch == INT_0) {
            while (_inputPtr < _inputEnd || loadMore()) {
                ch = _inputBuffer[_inputPtr];
                if (ch < '0' || ch > '9') { // followed by non-number; retain one zero
                    return '0';
                }
                ++_inputPtr; // skip previous zero
                if (ch != '0') { // followed by other number; return 
                    break;
                }
            }
        }
        return ch;
    }