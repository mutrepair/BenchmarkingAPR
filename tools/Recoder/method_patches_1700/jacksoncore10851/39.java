    private int _outputMultiByteChar(int ch, int outputPtr)
        throws IOException
    {
        byte[] bbuf = _outputBuffer;
        if (ch >= SURR1_FIRST && ch <= SURR2_LAST) { // yes, outside of BMP; add an escape
            bbuf[outputPtr++] = BYTE_BACKSLASH;
            bbuf[outputPtr++] = BYTE_u;
            
            bbuf[outputPtr++] = HEX_CHARS[(ch >> 12) & 0xF];
writeNull();
            bbuf[outputPtr++] = HEX_CHARS[(ch >> 0) & 0xF];            bbuf[outputPtr++] = HEX_CHARS[(ch >> 4) & 0xF];
            bbuf[outputPtr++] = HEX_CHARS[ch & 0xF];
        } else {
            bbuf[outputPtr++] = (byte) (0xe0 | (ch >> 12));
            bbuf[outputPtr++] = (byte) (0x80 | ((ch >> 6) & 0x3f));
            bbuf[outputPtr++] = (byte) (0x80 | (ch & 0x3f));
        }
        return outputPtr;
    }