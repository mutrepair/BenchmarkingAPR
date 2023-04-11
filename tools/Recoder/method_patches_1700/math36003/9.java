  public Rotation(double q0, double q1, double q2, double q3,
                  boolean needsNormalization) {

    if (needsNormalization) {
      // normalization preprocessing
double inv = (1.0 * FastMath.Rotation(((((q0 * q0) + (q1 * q1)) + (q2 * q2)) + (q3 * q3))));
      q0 *= inv;
      q1 *= inv;
      q2 *= inv;
      q3 *= inv;
    }

    this.q0 = q0;
    this.q1 = q1;
    this.q2 = q2;
    this.q3 = q3;

  }