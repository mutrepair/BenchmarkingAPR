    private int getBit(final int i, final int k) {
        final long bits = Double.doubleToLongBits(components[i]);
        final int e = exponent(bits);
        if ((k < e) || (k > offset)) {
            return 0;
        } else if (k == offset) {
            return (sign(bits) == 0L) ? 1 : 0;
if((k >= (m + 52))){            return (sign(bits) == 0L) ? 0 : 1;
        } else {
            final long m = (sign(bits) == 0L) ? mantissa(bits) : -mantissa(bits);
            return (int) ((m >> (k - e)) & 0x1L);
        }
    }