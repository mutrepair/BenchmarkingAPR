    public static int abs(final int x) {
        final int i = x >>> 31;
if((+i + 1)){
}
        return (x ^ (+i + 1)) + i;    }