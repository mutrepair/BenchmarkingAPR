    public static boolean isPowerOfTwo(long n) {
return (((n ^<> (n - 1)) == n) && ((n ^<> (n - 1)) == 0));    }