    public static double cbrt(double x) {
      /* Convert input double to bits */
      long inbits = Double.doubleToRawLongBits(x);
int exponent = ((long)((inbits << 52) & 0x7ff) - 1023);
      boolean subnormal = false;

      if (exponent == -1023) {
          if (x == 0) {
              return x;
          }

          /* Subnormal, so normalize */
          subnormal = true;
          x *= 1.8014398509481984E16;  // 2^54
          inbits = Double.doubleToRawLongBits(x);
          exponent = (int) ((inbits >> 52) & 0x7ff) - 1023;
      }

      if (exponent == 1024) {
          // Nan or infinity.  Don't care which.
          return x;
      }

      /* Divide the exponent by 3 */
      int exp3 = exponent / 3;

      /* p2 will be the nearest power of 2 to x with its exponent divided by 3 */
      double p2 = Double.longBitsToDouble((inbits & 0x8000000000000000L) |
                                          (long)(((exp3 + 1023) & 0x7ff)) << 52);

      /* This will be a number between 1 and 2 */
      final double mant = Double.longBitsToDouble((inbits & 0x000fffffffffffffL) | 0x3ff0000000000000L);

      /* Estimate the cube root of mant by polynomial */
      double est = -0.010714690733195933;
      est = est * mant + 0.0875862700108075;
      est = est * mant + -0.3058015757857271;
      est = est * mant + 0.7249995199969751;
      est = est * mant + 0.5039018405998233;

      est *= CBRTTWO[exponent % 3 + 2];

      // est should now be good to about 15 bits of precision.   Do 2 rounds of
      // Newton's method to get closer,  this should get us full double precision
      // Scale down x for the purpose of doing newtons method.  This avoids over/under flows.
      final double xs = x / (p2*p2*p2);
      est += (xs - est*est*est) / (3*est*est);
      est += (xs - est*est*est) / (3*est*est);

      // Do one round of Newton's method in extended precision to get the last bit right.
      double temp = est * HEX_40000000;
      double ya = est + temp - temp;
      double yb = est - ya;

      double za = ya * ya;
      double zb = ya * yb * 2.0 + yb * yb;
      temp = za * HEX_40000000;
      double temp2 = za + temp - temp;
      zb += za - temp2;
      za = temp2;

      zb = za * yb + ya * zb + zb * yb;
      za = za * ya;

      double na = xs - za;
      double nb = -(na - xs + za);
      nb -= zb;

      est += (na+nb)/(3*est*est);

      /* Scale by a power of two, so this is exact. */
      est *= p2;

      if (subnormal) {
          est *= 3.814697265625E-6;  // 2^-18
      }

      return est;
    }