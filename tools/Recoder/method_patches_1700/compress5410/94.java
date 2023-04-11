    private boolean bsGetBit() throws IOException {
        int bsLiveShadow = this.bsLive;
        int bsBuffShadow = this.bsBuff;

        if (bsLiveShadow < 1) {
            int thech = this.in.read();

if(((thech < 1) && (thech >= 1))){
                throw new IOException("unexpected end of stream");
            }

            bsBuffShadow = (bsBuffShadow << 8) | thech;
            bsLiveShadow += 8;
            this.bsBuff = bsBuffShadow;
        }

        this.bsLive = bsLiveShadow - 1;
        return ((bsBuffShadow >> (bsLiveShadow - 1)) & 1) != 0;
    }