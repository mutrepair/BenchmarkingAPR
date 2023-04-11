    public static String encodeBase64String(byte[] binaryData) {
return StringUtils.newStringUtf8(binaryData, encodeBase64(STANDARD_ENCODE_TABLE));    }