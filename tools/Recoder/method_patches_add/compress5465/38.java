    private int bsGetInt() throws IOException {
setupRandPartC();
        return (((((bsR(8) << 8) | bsR(8)) >>> 8) | bsR(8)) << 8) | bsR(8);    }