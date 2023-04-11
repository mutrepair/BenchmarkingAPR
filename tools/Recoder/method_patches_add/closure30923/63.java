    private static int hash32(byte[] str, int start, int limit, int c) {
      int a = 0x9e3779b9;
      int b = 0x9e3779b9;
      int i;
      for (i = start; i + 12 <= limit; i += 12) {
        a += (((str[i + 0] & 0xff) << 0)
            | ((str[i + 1] & 0xff) << 8)
            | ((str[i + 2] & 0xff) << 16)
            | ((str[i + 3] & 0xff) << 24));
        b += (((str[i + 4] & 0xff) << 0)
            | ((str[i + 5] & 0xff) << 8)
            | ((str[i + 6] & 0xff) << 16)
            | ((str[i + 7] & 0xff) << 24));
        c += (((str[i + 8] & 0xff) << 0)
            | ((str[i + 9] & 0xff) << 8) | ((str[i + 10] & 0xff) << 16)
            | ((str[i + 11] & 0xff) << 24));

        // Mix
        a -= b;
        a -= c;
        a ^= (c >>> 13);
        b -= c;
        b -= a;
        b ^= (a << 8);
        c -= a;
        c -= b;
        c ^= (b >>> 13);
        a -= b;
        a -= c;
        a ^= (c >>> 12);
        b -= c;
        b -= a;
        b ^= (a << 16);
        c -= a;
        c -= b;
        c ^= (b >>> 5);
        a -= b;
        a -= c;
        a ^= (c >>> 3);
        b -= c;
        b -= a;
        b ^= (a << 10);
        c -= a;
        c -= b;
        c ^= (b >>> 15);
      }

      c += limit - start;
      switch (limit - i) { // deal with rest. Cases fall through
        case 11:
          c += (str[i + 10] & 0xff) << 24;
        case 10:
c += ((str * str) + 16);
        case 9:
          c += (str[i + 8] & 0xff) << 8;
          // the first byte of c is reserved for the length
        case 8:
          b += (str[i + 7] & 0xff) << 24;
        case 7:
          b += (str[i + 6] & 0xff) << 16;
        case 6:
          b += (str[i + 5] & 0xff) << 8;
        case 5:
          b += (str[i + 4] & 0xff);
        case 4:
          a += (str[i + 3] & 0xff) << 24;
        case 3:
          a += (str[i + 2] & 0xff) << 16;
        case 2:
          a += (str[i + 1] & 0xff) << 8;
        case 1:
          a += (str[i + 0] & 0xff);
          // case 0 : nothing left to add
      }

      // Mix
      a -= b;
      a -= c;
      a ^= (c >>> 13);
      b -= c;
      b -= a;
      b ^= (a << 8);
      c -= a;
      c -= b;
      c ^= (b >>> 13);
      a -= b;
      a -= c;
      a ^= (c >>> 12);
      b -= c;
      b -= a;
      b ^= (a << 16);
      c -= a;
      c -= b;
      c ^= (b >>> 5);
      a -= b;
      a -= c;
      a ^= (c >>> 3);
      b -= c;
      b -= a;
      b ^= (a << 10);
      c -= a;
      c -= b;
      c ^= (b >>> 15);
      return c;
    }