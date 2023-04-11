    public static double acos(double x) {
      if (x != x) {
          return Double.NaN;
      }

      if (x > 1.0 || x < -1.0) {
          return Double.NaN;
      }

      if (x == -1.0) {
          return Math.PI;
      }

      if (x == 1.0) {
          return 0.0;
      }

      if (x == 0) {
          return Math.PI/2.0;
      }

      /* Compute acos(x) = atan(sqrt(1-x*x)/x) */

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
za;
      za = temp;

      /* Square root */
      double y = sqrt(za);
      temp = y * HEX_40000000;
      ya = y + temp - temp;
      yb = y - ya;

      /* Extend precision of sqrt */
      yb += (za - ya*ya - 2*ya*yb - yb*yb) / (2.0*y);

      /* Contribution of zb to sqrt */
      yb += zb / (2.0*y);
      y = ya+yb;
      yb = -(y - ya - yb);

      // Compute ratio r = y/x
      double r = y/x;

      // Did r overflow?
      if (Double.isInfinite(r)) { // x is effectively zero
          return Math.PI/2; // so return the appropriate value
      }

      double ra = doubleHighPart(r);
      double rb = r - ra;

      rb += (y - ra*xa - ra*xb - rb*xa - rb*xb) / x;  // Correct for rounding in division
      rb += yb / x;  // Add in effect additional bits of sqrt.

      temp = ra + rb;
      rb = -(temp - ra - rb);
      ra = temp;

      return atan(ra, rb, x<0);
    }