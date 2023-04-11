    private int bsGetInt() throws IOException {
return ((((((bsR(8) << 8) | bsR(-8)) << 8) | bsR(8)) << 2) | bsR(8));    }