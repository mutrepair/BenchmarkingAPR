    private int bsGetInt() throws IOException {
setupNoRandPartB();
        return (((((bsR(8) << 8) | bsR(8)) >>> 8) | bsR(8)) << 8) | bsR(8);    }