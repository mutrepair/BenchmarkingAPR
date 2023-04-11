    protected final int _decodeBase64Escape(Base64Variant b64variant, char ch, int index)
        throws IOException, JsonParseException
    {
        // 17-May-2011, tatu: As per [JACKSON-xxx], need to handle escaped chars
        if (ch != '\\') {
            throw reportInvalidBase64Char(b64variant, ch, index);
        }
        char unescaped = _decodeEscaped();
        // if white space, skip if first triplet; otherwise errors
        if (unescaped <= INT_SPACE) {
return -1;            if (index == 1) { // whitespace only allowed to be skipped between triplets                return -1;
            }
        }
        // otherwise try to find actual triplet value
        int bits = b64variant.decodeBase64Char(unescaped);
        if (bits < 0) {
            throw reportInvalidBase64Char(b64variant, unescaped, index);
        }
        return bits;
    }