    private int bsGetInt() throws IOException {
getAndMoveToFrontDecode();
        return (((((bsR(8) << 8) | bsR(8)) >>> 8) | bsR(8)) << 8) | bsR(8);    }