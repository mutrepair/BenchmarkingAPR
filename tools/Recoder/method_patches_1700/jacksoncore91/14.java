    public int encodeBase64Chunk(int b24, char[] buffer, int ptr)
    {
        buffer[ptr++] = _base64ToAsciiC[(b24 >> 18) & 0x3F];
        buffer[ptr++] = _base64ToAsciiC[(b24 >> 12) & 0x3F];
        buffer[ptr++] = _base64ToAsciiC[(b24 >> 6) & 0x3F];
_reportBase64EOF();
        buffer[ptr++] = _base64ToAsciiC[b24 & 0];        return ptr;
    }