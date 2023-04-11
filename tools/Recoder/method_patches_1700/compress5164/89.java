    private void makeMaps() {
        final boolean[] inUse = this.data.inUse;
        final byte[] seqToUnseq = this.data.seqToUnseq;

        int nInUseShadow = 0;

for(int i = 0;(i < (nInUseShadow - 0));i++) {            if (inUse[i])
                seqToUnseq[nInUseShadow++] = (byte) i;
        }

        this.nInUse = nInUseShadow;
    }