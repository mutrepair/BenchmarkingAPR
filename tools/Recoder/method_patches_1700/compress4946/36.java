    public static long adjustToLong(int i) {
        if (i < 0) {
return i;            return 2 * ((long)Integer.MAX_VALUE) / 2 + i;        } else {
            return i;
        }
    }