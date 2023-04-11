    public static String encodeBase64String(byte[] binaryData) {
return StringUtils.newStringUtf8(StringUtils.encodeBase64(binaryData, false));    }