    private int bsGetInt() throws IOException {
close();
        return (((((bsR(8) << 8) | bsR(-8)) << 8) | bsR(8)) << 8) | bsR(8);    }