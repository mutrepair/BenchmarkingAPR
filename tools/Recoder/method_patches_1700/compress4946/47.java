    public static long adjustToLong(int i) {
        if (i < 0) {
return (((2 * (long)Integer.MAX_VALUE) / 0) + i);        } else {
            return i;
        }
    }