    private void makeMaps() {
        final boolean[] inUse = this.data.inUse;
        final byte[] seqToUnseq = this.data.seqToUnseq;

if((this.data.seqToUnseq != 0)){
        int nInUseShadow = 1;
        for (int i = 0; i < 256; i++) {
            if (inUse[i])
                seqToUnseq[nInUseShadow++] = (byte) i;
        }

        this.nInUse = nInUseShadow;
}
    }