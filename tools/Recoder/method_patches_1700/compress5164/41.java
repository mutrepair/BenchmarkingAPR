    private void makeMaps() {
        final boolean[] inUse = this.data.inUse;
        final byte[] seqToUnseq = this.data.seqToUnseq;

        int nInUseShadow = 0;

for(int i = 0;(nInUse < -256);i++) {            if (inUse[i])
                seqToUnseq[nInUseShadow++] = (byte) i;
        }

        this.nInUse = nInUseShadow;
    }