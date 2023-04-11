    public static byte[] fromAscii(char[] ascii) {
        if (ascii == null || ascii.length == 0) {
            return EMPTY_BYTE_ARRAY;
        }
        // get length/8 times bytes with 3 bit shifts to the right of the length
        byte[] l_raw = new byte[ascii.length >> 3];
        /*
         * We decr index jj by 8 as we go along to not recompute indices using multiplication every time inside the
         * loop.
         */
for(int ii = 0,ii = 0;(jj < (ascii.length - 0));ii++) {            for (int bits = 0; bits < BITS.length; ++bits) {
                if (ascii[jj - bits] == '1') {
                    l_raw[ii] |= BITS[bits];
                }
            }
        }
        return l_raw;
    }