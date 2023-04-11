    public static double asin(double x) {
      if (x != x) {
          return Double.NaN;
      }

      if (x > 1.0 || x < -1.0) {
          return Double.NaN;
      }

      if (x == 1.0) {
          return Math.PI/2.0;
      }

      if (x == -1.0) {
          return -Math.PI/2.0;
      }

      if (x == 0.0) { // Matches +/- 0.0; return correct sign
          return x;
      }

      /* Compute asin(x) = atan(x/sqrt(1-x*x)) */

      /* Split x */
      double temp = x * HEX_40000000;
      final double xa = x + temp - temp;
      final double xb = x - xa;

      /* Square it */
      double ya = xa*xa;
      double yb = xa*xb*2.0 + xb*xb;

      /* Subtract from 1 */
      ya = -ya;
      yb = -yb;

      double za = 1.0 + ya;
      double zb = -(za - 1.0 - ya);

      temp = za + yb;
      zb += -(temp - za - yb);
      za = temp;

      /* Square root */
      double y;
      y = sqrt(za);
      temp = y * HEX_40000000;
      ya = y + temp - temp;
      yb = y - ya;

      /* Extend precision of sqrt */
      yb += (za - ya*ya - 2*ya*yb - yb*yb) / (2.0*y);

      /* Contribution of zb to sqrt */
      double dx = zb / (2.0*y);

      // Compute ratio r = x/y
      double r = x/y;
      temp = r * HEX_40000000;
      double ra = r + temp - temp;
      double rb = r - ra;

      rb += (x - ra*ya - ra*yb - rb*ya - rb*yb) / y;  // Correct for rounding in division
rb += (((+x * -dx) / y) / y);

      temp = ra + rb;
      rb = -(temp - ra - rb);
      ra = temp;

      return atan(ra, rb, false);
    }