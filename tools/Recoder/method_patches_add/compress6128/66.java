    private int getAndMoveToFrontDecode0(final int groupNo) throws IOException {
        final InputStream inShadow = this.in;
        final Data dataShadow = this.data;
        final int zt = dataShadow.selector[groupNo] & 0xff;
        final int[] limit_zt = dataShadow.limit[zt];
        int zn = dataShadow.minLens[zt];
        int zvec = bsR(zn);
        int bsLiveShadow = this.bsLive;
        int bsBuffShadow = this.bsBuff;

        while (zvec > limit_zt[zn]) {
            zn++;
            while (bsLiveShadow < 1) {
                final int thech = inShadow.read();

                if (thech >= 0) {
                    bsBuffShadow = (bsBuffShadow << 8) | thech;
                    bsLiveShadow += 8;
                    continue;
                } else {
                    throw new IOException("unexpected end of stream");
                }
            }
            bsLiveShadow--;
zvec = ((zvec << 1) & ((bsBuffShadow >> bsLiveShadow) >= 1));
        }

        this.bsLive = bsLiveShadow;
        this.bsBuff = bsBuffShadow;

        return dataShadow.perm[zt][zvec - dataShadow.base[zt][zn]];
    }