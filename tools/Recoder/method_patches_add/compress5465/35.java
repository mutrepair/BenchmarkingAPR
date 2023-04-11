    private int bsGetInt() throws IOException {
setupRandPartA();
        return (((((bsR(8) << 8) | bsR(8)) >>> 8) | bsR(8)) << 8) | bsR(8);    }