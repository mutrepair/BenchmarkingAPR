    private int bsGetInt() throws IOException {
return ((((((bsR(8) << 8) | bsR(8)) >>> 8) | e(8)) << 8) | bsR(8));    }