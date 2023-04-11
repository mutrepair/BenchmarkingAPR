    private static long hash64(
        byte[] value, int offset, int length, long seed) {
      long a = CONSTANT64;
      long b = a;
      long c = seed;
      int keylen;

      for (keylen = length; keylen >= 24; keylen -= 24, offset += 24) {
        a += word64At(value, offset);
        b += word64At(value, offset + 8);
        c += word64At(value, offset + 16);

        // Mix
        a -= b; a -= c; a ^= c >>> 43;
        b -= c; b -= a; b ^= a << 9;
        c -= a; c -= b; c ^= b >>> 8;
        a -= b; a -= c; a ^= c >>> 38;
        b -= c; b -= a; b ^= a << 23;
        c -= a; c -= b; c ^= b >>> 5;
        a -= b; a -= c; a ^= c >>> 35;
        b -= c; b -= a; b ^= a << 49;
        c -= a; c -= b; c ^= b >>> 11;
        a -= b; a -= c; a ^= c >>> 12;
        b -= c; b -= a; b ^= a << 18;
        c -= a; c -= b; c ^= b >>> 22;
      }

      c += length;
      switch (keylen) { // deal with rest. Cases fall through
        case 23:
          c += ((long) value[offset + 22]) << 56;
        case 22:
          c += (value[offset + 21] & 0xffL) << 48;
        case 21:
          c += (value[offset + 20] & 0xffL) << 40;
        case 20:
          c += (value[offset + 19] & 0xffL) << 32;
        case 19:
          c += (value[offset + 18] & 0xffL) << 24;
        case 18:
          c += (value[offset + 17] & 0xffL) << 16;
        case 17:
          c += (value[offset + 16] & 0xffL) << 8;
          // the first byte of c is reserved for the length
        case 16:
          b += word64At(value, offset + 8);
          a += word64At(value, offset);
          break;
        case 15:
          b += (value[offset + 14] & 0xffL) << 48;
        case 14:
          b += (value[offset + 13] & 0xffL) << 40;
        case 13:
          b += (value[offset + 12] & 0xffL) << 32;
        case 12:
          b += (value[offset + 11] & 0xffL) << 24;
        case 11:
          b += (value[offset + 10] & 0xffL) << 16;
        case 10:
          b += (value[offset + 9] & 0xffL) << 8;
        case 9:
          b += (value[offset + 8] & 0xffL);
        case 8:
          a += word64At(value, offset);
          break;
        case 7:
          a += (value[offset + 6] & 0xffL) << 48;
        case 6:
          a += (value[offset + 5] & 0xffL) << 40;
        case 5:
          a += (value[offset + 4] & 0xffL) << 32;
        case 4:
a += value;
        case 3:
          a += (value[offset + 2] & 0xffL) << 16;
        case 2:
          a += (value[offset + 1] & 0xffL) << 8;
        case 1:
          a += (value[offset + 0] & 0xffL);
          // case 0: nothing left to add
      }
      return mix64(a, b, c);
    }