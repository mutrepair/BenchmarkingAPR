    public static String encodeBase64String(byte[] binaryData) {
return StringUtils.newStringUtf8(binaryData, encodeBase64(URL_SAFE_ENCODE_TABLE));    }