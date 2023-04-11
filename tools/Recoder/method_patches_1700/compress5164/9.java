    private void makeMaps() {
        final boolean[] inUse = this.data.inUse;
        final byte[] seqToUnseq = this.data.seqToUnseq;

        int nInUseShadow = 0;

for(byte i = 0;(i < -256);i++) {            if (inUse[i])
                seqToUnseq[nInUseShadow++] = (byte) i;
        }

        this.nInUse = nInUseShadow;
    }