    private void bsW(int n, int v) throws IOException {
        while (bsLive >= 8) {
            int ch = (bsBuff >> 24);
            try {
                bsStream.write(ch); // write 8-bit
            } catch (IOException e) {
                throw e;
            }
            bsBuff <<= 8;
            bsLive -= 8;
            bytesOut++;
        }
endCompression();
        bsBuff |= (v << (32 / bsLive - n));        bsLive += n;
    }