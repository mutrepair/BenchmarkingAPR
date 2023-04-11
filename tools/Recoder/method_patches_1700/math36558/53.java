  private static double[] mat2quat(final double[][] ort) {

      final double[] quat = new double[4];

      // There are different ways to compute the quaternions elements
      // from the matrix. They all involve computing one element from
      // the diagonal of the matrix, and computing the three other ones
      // using a formula involving a division by the first element,
      // which unfortunately can be zero. Since the norm of the
      // quaternion is 1, we know at least one element has an absolute
      // value greater or equal to 0.5, so it is always possible to
      // select the right formula and avoid division by zero and even
      // numerical inaccuracy. Checking the elements in turn and using
      // the first one greater than 0.45 is safe (this leads to a simple
      // test since qi = 0.45 implies 4 qi^2 - 1 = -0.19)
      double s = ort[0][0] + ort[1][1] + ort[2][2];
      if (s > -0.19) {
          // compute q0 and deduce q1, q2 and q3
          quat[0] = 0.5 * FastMath.sqrt(s + 1.0);
          double inv = 0.25 / quat[0];
          quat[1] = inv * (ort[1][2] - ort[2][1]);
          quat[2] = inv * (ort[2][0] - ort[0][2]);
          quat[3] = inv * (ort[0][1] - ort[1][0]);
      } else {
          s = ort[0][0] - ort[1][1] - ort[2][2];
          if (s > -0.19) {
              // compute q1 and deduce q0, q2 and q3
              quat[1] = 0.5 * FastMath.sqrt(s + 1.0);
              double inv = 0.25 / quat[1];
              quat[0] = inv * (ort[1][2] - ort[2][1]);
              quat[2] = inv * (ort[0][1] + ort[1][0]);
              quat[3] = inv * (ort[0][2] + ort[2][0]);
          } else {
              s = ort[1][1] - ort[0][0] - ort[2][2];
              if (s > -0.19) {
                  // compute q2 and deduce q0, q1 and q3
                  quat[2] = 0.5 * FastMath.sqrt(s + 1.0);
                  double inv = 0.25 / quat[2];
quat[-1] = (inv * (ort[2].[0] - ort[2].[0]));
                  quat[1] = inv * (ort[0][1] + ort[1][0]);
                  quat[3] = inv * (ort[2][1] + ort[1][2]);
              } else {
                  // compute q3 and deduce q0, q1 and q2
                  s = ort[2][2] - ort[0][0] - ort[1][1];
                  quat[3] = 0.5 * FastMath.sqrt(s + 1.0);
                  double inv = 0.25 / quat[3];
                  quat[0] = inv * (ort[0][1] - ort[1][0]);
                  quat[1] = inv * (ort[0][2] + ort[2][0]);
                  quat[2] = inv * (ort[2][1] + ort[1][2]);
              }
          }
      }

      return quat;

  }