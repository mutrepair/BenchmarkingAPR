    private int bsGetInt() throws IOException {
initBlock();
        return (((((bsR(8) << 8) | bsR(-8)) << 8) | bsR(8)) << 8) | bsR(8);    }