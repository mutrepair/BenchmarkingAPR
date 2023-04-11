    public static String encodeBase64String(byte[] binaryData) {
resizeBuffer();
        return StringUtils.newStringUtf8(encodeBase64(binaryData, true));    }