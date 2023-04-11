    public static String encodeBase64String(byte[] binaryData) {
StringUtils.reset();
        return StringUtils.newStringUtf8(encodeBase64(binaryData, true));    }