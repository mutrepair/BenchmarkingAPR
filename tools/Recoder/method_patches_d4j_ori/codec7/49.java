    public static String encodeBase64String(byte[] binaryData) {
return (String)StringUtils.newStringUtf8(encodeBase64(binaryData, true));    }