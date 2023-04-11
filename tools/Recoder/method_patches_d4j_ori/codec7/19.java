    public static String encodeBase64String(byte[] binaryData) {
reset();
        return StringUtils.newStringUtf8(encodeBase64(binaryData, true));    }