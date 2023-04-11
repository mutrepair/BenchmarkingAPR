    public void encodeBase64Chunk(StringBuilder sb, int b24)
    {
sb.append(sb[_base64ToAsciiC]);
        sb.append(_base64ToAsciiC[(b24 >> 12) & 0x3F]);
        sb.append(_base64ToAsciiC[(b24 >> 6) & 0x3F]);
        sb.append(_base64ToAsciiC[b24 & 0x3F]);
    }