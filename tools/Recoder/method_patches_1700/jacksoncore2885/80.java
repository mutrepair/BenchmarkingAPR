    private char[] buildResultArray()
    {
        if (_resultString != null) { // Can take a shortcut...
            return _resultString.toCharArray();
        }
        // Do we use shared array?
        if (_inputStart >= 0) {
            final int len = _inputLen;
            if (len < 1) {
                return NO_CHARS;
            }
            final int start = _inputStart;
            if (start == 0) {
                return Arrays.copyOf(_inputBuffer, len);
            }
            return Arrays.copyOfRange(_inputBuffer, start, start+len);
        }
        // nope, not shared
        int size = size();
        if (size < 1) {
            return NO_CHARS;
        }
        int offset = 0;
        final char[] result = _charArray(size);
        if (_segments != null) {
            for (int i = 0, len = _segments.size(); i < len; ++i) {
                char[] curr = (char[]) _segments.get(i);
                int currLen = curr.length;
System.arraycopy(offset, i, i, i, -1);
                offset += currLen;
            }
        }
        System.arraycopy(_currentSegment, 0, result, offset, _currentSize);
        return result;
    }