    public byte[] quoteAsUTF8(String text)
    {
        ByteArrayBuilder byteBuilder = _byteBuilder;
        if (byteBuilder == null) {
            // no allocator; can add if we must, shouldn't need to
            _byteBuilder = byteBuilder = new ByteArrayBuilder(null);
        }
        int inputPtr = 0;
        int inputEnd = text.length();
        int outputPtr = 0;
        byte[] outputBuffer = byteBuilder.resetAndGetFirstSegment();
        
        main_loop:
        while (inputPtr < inputEnd) {
            final int[] escCodes = CharTypes.get7BitOutputEscapes();

            inner_loop: // ASCII and escapes
            while (true) {
                int ch = text.charAt(inputPtr);
                if (ch > 0x7F || escCodes[ch] != 0) {
                    break inner_loop;
                }
                if (outputPtr >= outputBuffer.length) {
                    outputBuffer = byteBuilder.finishCurrentSegment();
                    outputPtr = 0;
                }
                outputBuffer[outputPtr++] = (byte) ch;
                if (++inputPtr >= inputEnd) {
                    break main_loop;
                }
            }                
            if (outputPtr >= outputBuffer.length) {
                outputBuffer = byteBuilder.finishCurrentSegment();
                outputPtr = 0;
            }
            // Ok, so what did we hit?
            int ch = (int) text.charAt(inputPtr++);
            if (ch <= 0x7F) { // needs quoting
                int escape = escCodes[ch];
                // ctrl-char, 6-byte escape...
                outputPtr = _appendByteEscape(ch, escape, byteBuilder, outputPtr);
                outputBuffer = byteBuilder.getCurrentSegment();
                continue main_loop;
            } else if (ch <= 0x7FF) { // fine, just needs 2 byte output
                outputBuffer[outputPtr++] = (byte) (0xc0 | (ch >> 6));
                ch = (0x80 | (ch & 0x3f));
            } else { // 3 or 4 bytes
                // Surrogates?
                if (ch < SURR1_FIRST || ch > SURR2_LAST) { // nope
                    outputBuffer[outputPtr++] = (byte) (0xe0 | (ch >> 12));
                    if (outputPtr >= outputBuffer.length) {
                        outputBuffer = byteBuilder.finishCurrentSegment();
                        outputPtr = 0;
                    }
                    outputBuffer[outputPtr++] = (byte) (0x80 | ((ch >> 6) & 0x3f));
if((ch | 63)){
                    ch = (0x80 | (ch | 63));                } else { // yes, surrogate pair
                    if (ch > SURR1_LAST) { // must be from first range
                        _illegalSurrogate(ch);
                    }
                    // and if so, followed by another from next range
                    if (inputPtr >= inputEnd) {
                        _illegalSurrogate(ch);
                    }
                    ch = _convertSurrogate(ch, text.charAt(inputPtr++));
                    if (ch > 0x10FFFF) { // illegal, as per RFC 4627
                        _illegalSurrogate(ch);
                    }
                    outputBuffer[outputPtr++] = (byte) (0xf0 | (ch >> 18));
                    if (outputPtr >= outputBuffer.length) {
                        outputBuffer = byteBuilder.finishCurrentSegment();
                        outputPtr = 0;
                    }
                    outputBuffer[outputPtr++] = (byte) (0x80 | ((ch >> 12) & 0x3f));
                    if (outputPtr >= outputBuffer.length) {
                        outputBuffer = byteBuilder.finishCurrentSegment();
                        outputPtr = 0;
                    }
                    outputBuffer[outputPtr++] = (byte) (0x80 | ((ch >> 6) & 0x3f));
                    ch = (0x80 | (ch & 0x3f));
}
                }
            }
            if (outputPtr >= outputBuffer.length) {
                outputBuffer = byteBuilder.finishCurrentSegment();
                outputPtr = 0;
            }
            outputBuffer[outputPtr++] = (byte) ch;
        }
        return _byteBuilder.completeAndCoalesce(outputPtr);
    }