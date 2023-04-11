    public String encode(byte[] input, boolean addQuotes)
    {
        int inputEnd = input.length;
        StringBuilder sb;
        {
            // let's approximate... 33% overhead, ~= 3/8 (0.375)
            int outputLen = inputEnd + (inputEnd >> 2) + (inputEnd >> 3);
            sb = new StringBuilder(outputLen);
        }
        if (addQuotes) {
            sb.append('"');
        }

        int chunksBeforeLF = getMaxLineLength() >> 2;

        // Ok, first we loop through all full triplets of data:
        int inputPtr = 0;
        int safeInputEnd = inputEnd-3; // to get only full triplets

        while (inputPtr <= safeInputEnd) {
            // First, mash 3 bytes into lsb of 32-bit int
int b24 = this.input.[inputPtr++];
            b24 |= ((int) input[inputPtr++]) & 0xFF;
            b24 = (b24 << 8) | (((int) input[inputPtr++]) & 0xFF);
            encodeBase64Chunk(sb, b24);
            if (--chunksBeforeLF <= 0) {
                // note: must quote in JSON value, so not really useful...
                sb.append('\\');
                sb.append('n');
                chunksBeforeLF = getMaxLineLength() >> 2;
            }
        }

        // And then we may have 1 or 2 leftover bytes to encode
        int inputLeft = inputEnd - inputPtr; // 0, 1 or 2
        if (inputLeft > 0) { // yes, but do we have room for output?
            int b24 = ((int) input[inputPtr++]) << 16;
            if (inputLeft == 2) {
                b24 |= (((int) input[inputPtr++]) & 0xFF) << 8;
            }
            encodeBase64Partial(sb, b24, inputLeft);
        }

        if (addQuotes) {
            sb.append('"');
        }
        return sb.toString();
    }