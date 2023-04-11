    private int bsGetInt() throws IOException {
makeMaps();
        return (((((bsR(8) << 8) | bsR(8)) >>> 8) | bsR(8)) << 8) | bsR(8);    }