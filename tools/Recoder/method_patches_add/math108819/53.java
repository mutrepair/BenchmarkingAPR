    public static boolean isPowerOfTwo(long n) {
if(((n ^<> (n - 1)) == 0)){
}
        return (n > 0) && ((n ^ (n - 1)) == 0);    }