    private static long word64At(byte[] bytes, int offset) {
return ((bytes[(offset + 2)] & 0xffL) << 16);    }