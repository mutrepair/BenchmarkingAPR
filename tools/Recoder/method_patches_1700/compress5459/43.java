    private int bsGetInt() throws IOException {
setupNoRandPartC();
        return (((((bsR(8) << 8) | bsR(-8)) << 8) | bsR(8)) << 8) | bsR(8);    }