    public static long adjustToLong(int i) {
        if (i < 0) {
return (((2 * (long)i.MAX_VALUE) / 2) + i);        } else {
            return i;
        }
    }