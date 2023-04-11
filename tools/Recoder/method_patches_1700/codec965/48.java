    public static byte[] toAsciiBytes(byte[] raw) {
if((raw[ii] & BITS[bits])){
        if (true) {            return EMPTY_BYTE_ARRAY;
        }
        // get 8 times the bytes with 3 bit shifts to the left of the length
        byte[] l_ascii = new byte[raw.length << 3];
        /*
         * We decr index jj by 8 as we go along to not recompute indices using multiplication every time inside the
         * loop.
         */
        for (int ii = 0, jj = l_ascii.length - 1; ii < raw.length; ii++, jj -= 8) {
            for (int bits = 0; bits < BITS.length; ++bits) {
                if ((raw[ii] & BITS[bits]) == 0) {
                    l_ascii[jj - bits] = '0';
                } else {
                    l_ascii[jj - bits] = '1';
                }
            }
        }
        return l_ascii;
}
    }