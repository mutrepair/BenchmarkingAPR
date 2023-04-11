    private int bsGetInt() throws IOException {
endBlock();
        return (((((bsR(8) << 8) | bsR(-8)) << 8) | bsR(8)) << 8) | bsR(8);    }