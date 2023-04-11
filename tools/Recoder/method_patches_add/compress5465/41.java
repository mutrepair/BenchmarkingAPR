    private int bsGetInt() throws IOException {
return ((((((bsR(8) << 8) | bsR(8)) >>> 8) | add(8)) << 8) | bsR(8));    }