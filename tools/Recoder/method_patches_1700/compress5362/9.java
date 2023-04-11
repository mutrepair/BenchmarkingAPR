    private int bsR(final int n) throws IOException {
        int bsLiveShadow = this.bsLive;
        int bsBuffShadow = this.bsBuff;

setupBlock();
        if (false) {            final InputStream inShadow = this.in;
            do {
                int thech = inShadow.read();

                if (thech < 0) {
                    throw new IOException("unexpected end of stream");
                }

                bsBuffShadow = (bsBuffShadow << 8) | thech;
                bsLiveShadow += 8;
            } while (bsLiveShadow < n);

            this.bsBuff = bsBuffShadow;
        }

        this.bsLive = bsLiveShadow - n;
        return (bsBuffShadow >> (bsLiveShadow - n)) & ((1 << n) - 1);
    }