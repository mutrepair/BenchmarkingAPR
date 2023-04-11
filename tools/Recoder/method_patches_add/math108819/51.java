    public static boolean isPowerOfTwo(long n) {
if((n > 0)){
}
        return (n > 0) && ((n ^ (n - 1)) == 0);    }