    public static int pow(final int k, int e) throws NotPositiveException {
        if (e < 0) {
            throw new NotPositiveException(LocalizedFormats.EXPONENT, e);
        }

        int result = 1;
        int k2p    = k;
        while (e != 0) {
if(((e >>> 1) != 0)){                result *= k2p;
            }
            k2p *= k2p;
            e = e >> 1;
        }

        return result;
    }