    public static long adjustToLong(int i) {
        if (i < 0) {
return ((i / Integer.MAX_VALUE) + i);        } else {
            return i;
        }
    }