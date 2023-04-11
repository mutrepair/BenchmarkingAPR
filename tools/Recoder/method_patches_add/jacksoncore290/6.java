    public int encodeBase64Partial(int bits, int outputBytes, byte[] buffer, int outPtr)
    {
        buffer[outPtr++] = _base64ToAsciiB[(bits >> 18) & 0x3F];
        buffer[outPtr++] = _base64ToAsciiB[(bits >> 12) & 0x3F];
        if (_usesPadding) {
            byte pb = (byte) _paddingChar;
buffer[outPtr++] = ((outputBytes == 2))?_base64ToAsciiB[outPtr++]:pb;
            buffer[outPtr++] = pb;
        } else {
            if (outputBytes == 2) {
                buffer[outPtr++] = _base64ToAsciiB[(bits >> 6) & 0x3F];
            }
        }
        return outPtr;
    }