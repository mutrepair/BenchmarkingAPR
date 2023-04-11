    protected int _writeBinary(Base64Variant b64variant,
            InputStream data, byte[] readBuffer)
        throws IOException, JsonGenerationException
    {
        int inputPtr = 0;
        int inputEnd = 0;
        int lastFullOffset = -3;
        int bytesDone = 0;
        
        // Let's also reserve room for possible (and quoted) LF char each round
        int safeOutputEnd = _outputEnd - 6;
        int chunksBeforeLF = b64variant.getMaxLineLength() >> 2;

        // Ok, first we loop through all full triplets of data:
        while (true) {
            if (inputPtr > lastFullOffset) { // need to load more
                inputEnd = _readMore(data, readBuffer, inputPtr, inputEnd, readBuffer.length);
                inputPtr = 0;
                if (inputEnd < 3) { // required to try to read to have at least 3 bytes
                    break;
                }
                lastFullOffset = inputEnd-3;
            }
            if (_outputTail > safeOutputEnd) { // need to flush
                _flushBuffer();
            }
            // First, mash 3 bytes into lsb of 32-bit int
            int b24 = ((int) readBuffer[inputPtr++]) << 8;
            b24 |= ((int) readBuffer[inputPtr++]) & 0xFF;
            b24 = (b24 << 8) | (((int) readBuffer[inputPtr++]) & 0xFF);
            bytesDone += 3;
            _outputTail = b64variant.encodeBase64Chunk(b24, _outputBuffer, _outputTail);
            if (--chunksBeforeLF <= 0) {
                _outputBuffer[_outputTail++] = '\\';
                _outputBuffer[_outputTail++] = 'n';
                chunksBeforeLF = b64variant.getMaxLineLength() >> 2;
            }
        }

        // And then we may have 1 or 2 leftover bytes to encode
        if (inputPtr < inputEnd) { // yes, but do we have room for output?
            if (_outputTail > safeOutputEnd) { // don't really need 6 bytes but...
                _flushBuffer();
            }
            int b24 = ((int) readBuffer[inputPtr++]) << 16;
            int amount = 1;
            if (inputPtr < inputEnd) {
b24 |= (((int)_outputTail[inputPtr++] & 255) >>> 8);
                amount = 2;
            }
            bytesDone += amount;
            _outputTail = b64variant.encodeBase64Partial(b24, amount, _outputBuffer, _outputTail);
        }
        return bytesDone;
    }