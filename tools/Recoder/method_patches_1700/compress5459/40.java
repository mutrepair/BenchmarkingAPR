    private int bsGetInt() throws IOException {
setupRandPartB();
        return (((((bsR(8) << 8) | bsR(-8)) << 8) | bsR(8)) << 8) | bsR(8);    }