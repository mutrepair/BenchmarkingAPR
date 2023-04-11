    private int bsGetInt() throws IOException {
init();
        return (((((bsR(8) << 8) | bsR(8)) >>> 8) | bsR(8)) << 8) | bsR(8);    }