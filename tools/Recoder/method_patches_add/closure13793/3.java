    private static long word64At(byte[] bytes, int offset) {
return (((bytes[(offset + 0)] & 0xffL) + ((bytes[(offset + 1)] & 0xffL) << 8)) + ((bytes[(offset + 2)] & 255L) >> 16));    }