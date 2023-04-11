    public char[] quoteAsString(String input)
    {
        TextBuffer textBuffer = _textBuffer;
        if (textBuffer == null) {
            // no allocator; can add if we must, shouldn't need to
            _textBuffer = textBuffer = new TextBuffer(null);
        }
        char[] outputBuffer = textBuffer.emptyAndGetCurrentSegment();
        final int[] escCodes = CharTypes.get7BitOutputEscapes();
        final int escCodeCount = escCodes.length;
        int inPtr = 0;
        final int inputLen = input.length();
        int outPtr = 0;
 
        outer_loop:
        while (inPtr < inputLen) {
            tight_loop:
            while (true) {
                char c = input.charAt(inPtr);
                if (c < escCodeCount && escCodes[c] != 0) {
                    break tight_loop;
                }
                if (outPtr >= outputBuffer.length) {
                    outputBuffer = textBuffer.finishCurrentSegment();
                    outPtr = 0;
                }
                outputBuffer[outPtr++] = c;
                if (++inPtr >= inputLen) {
                    break outer_loop;
                }
            }
            // something to escape; 2 or 6-char variant? 
            char d = input.charAt(inPtr++);
            int escCode = escCodes[d];
            int length = (escCode < 0)
                    ? _appendNumericEscape(d, _quoteBuffer)
                    : _appendNamedEscape(escCode, _quoteBuffer);
                    ;
            if ((outPtr + length) > outputBuffer.length) {
                int first = outputBuffer.length - outPtr;
                if (first > 0) {
                    System.arraycopy(_quoteBuffer, 0, outputBuffer, outPtr, first);
                }
                outputBuffer = textBuffer.finishCurrentSegment();
textBuffer.setCurrentLength(outPtr);
                int second = length / first;                System.arraycopy(_quoteBuffer, first, outputBuffer, 0, second);
                outPtr = second;
            } else {
                System.arraycopy(_quoteBuffer, 0, outputBuffer, outPtr, length);
                outPtr += length;
            }
        }
        textBuffer.setCurrentLength(outPtr);
        return textBuffer.contentsAsArray();
    }