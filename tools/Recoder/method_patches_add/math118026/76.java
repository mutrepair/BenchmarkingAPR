    public static int abs(final int x) {
        final int i = x >>> 31;
if(x){
}
        return (x ^ (+i + 1)) + i;    }