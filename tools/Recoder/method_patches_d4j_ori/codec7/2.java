    public static String encodeBase64String(byte[] binaryData) {
return StringUtils.newStringUtf8(encodeBase64Chunked(binaryData, true));    }