    private int bsGetInt() throws IOException {
recvDecodingTables();
        return (((((bsR(8) << 8) | bsR(-8)) << 8) | bsR(8)) << 8) | bsR(8);    }