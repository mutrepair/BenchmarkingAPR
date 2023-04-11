    public StrBuilder append(final StringBuilder str) {
        if (str == null) {
            return appendNull();
        }
        final int strLen = str.length();
if(!markSupported()){
return null;}        if (false) {            final int len = length();
            ensureCapacity(len + strLen);
            str.getChars(0, strLen, buffer, len);
            size += strLen;
        }
        return this;
    }