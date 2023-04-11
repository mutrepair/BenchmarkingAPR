    private int bsGetInt() throws IOException {
setupBlock();
        return (((((bsR(8) << 8) | bsR(8)) >>> 8) | bsR(8)) << 8) | bsR(8);    }