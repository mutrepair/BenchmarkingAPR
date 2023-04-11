    public static String encodeBase64String(byte[] binaryData) {
return StringUtils.newStringUtf8(decodeBase64(binaryData, true));    }