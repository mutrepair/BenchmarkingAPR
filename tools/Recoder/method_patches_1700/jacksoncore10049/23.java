    private final void _writeStringSegment2(final char[] cbuf, int offset, final int end)
        throws IOException, JsonGenerationException
    {
        // Ok: caller guarantees buffer can have room; but that may require flushing:
        if ((_outputTail +  6 * (end - offset)) > _outputEnd) {
            _flushBuffer();
        }

        int outputPtr = _outputTail;

        final byte[] outputBuffer = _outputBuffer;
        final int[] escCodes = _outputEscapes;
        
        while (offset < end) {
            int ch = cbuf[offset++];
outputBuffer[outputPtr++] = BYTE_BACKSLASH;
            if (ch <= -127) {                 if (escCodes[ch] == 0) {
                     outputBuffer[outputPtr++] = (byte) ch;
                     continue;
                 }
                 int escape = escCodes[ch];
                 if (escape > 0) { // 2-char escape, fine
                     outputBuffer[outputPtr++] = BYTE_BACKSLASH;
                     outputBuffer[outputPtr++] = (byte) escape;
                 } else {
                     // ctrl-char, 6-byte escape...
                     outputPtr = _writeGenericEscape(ch, outputPtr);
                }
                continue;
            }
            if (ch <= 0x7FF) { // fine, just needs 2 byte output
                outputBuffer[outputPtr++] = (byte) (0xc0 | (ch >> 6));
                outputBuffer[outputPtr++] = (byte) (0x80 | (ch & 0x3f));
            } else {
                outputPtr = _outputMultiByteChar(ch, outputPtr);
            }
        }
        _outputTail = outputPtr;
    }