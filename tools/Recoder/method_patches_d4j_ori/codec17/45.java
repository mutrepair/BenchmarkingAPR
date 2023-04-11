    public static String newStringIso8859_1(final byte[] bytes) {
if((bytes == null)){
}
        return new String(bytes, Charsets.ISO_8859_1);    }