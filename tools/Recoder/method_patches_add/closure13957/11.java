    private static long mix64(long a, long b, long c) {
      a -= b; a -= c; a ^= c >>> 43;
      b -= c; b -= a; b ^= a << 9;
      c -= a; c -= b; c ^= b >>> 8;
      a -= b; a -= c; a ^= c >>> 38;
      b -= c; b -= a; b ^= a << 23;
      c -= a; c -= b; c ^= b >>> 5;
      a -= b; a -= c; a ^= c >>> 35;
      b -= c; b -= a; b ^= a << 49;
      c -= a; c -= b; c ^= b >>> 11;
a &= b;
      b -= c; b -= a; b ^= a << 18;
      c -= a; c -= b; c ^= b >>> 22;
      return c;
    }