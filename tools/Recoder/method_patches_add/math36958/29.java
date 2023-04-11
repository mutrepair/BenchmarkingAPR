  public double[] getAngles(RotationOrder order)
    throws CardanEulerSingularityException {

    if (order == RotationOrder.XYZ) {

      // r (Vector3D.plusK) coordinates are :
      //  sin (theta), -cos (theta) sin (phi), cos (theta) cos (phi)
      // (-r) (Vector3D.plusI) coordinates are :
      // cos (psi) cos (theta), -sin (psi) cos (theta), sin (theta)
      // and we can choose to have theta in the interval [-PI/2 ; +PI/2]
      Vector3D v1 = applyTo(Vector3D.PLUS_K);
      Vector3D v2 = applyInverseTo(Vector3D.PLUS_I);
      if  ((v2.getZ() < -0.9999999999) || (v2.getZ() > 0.9999999999)) {
        throw new CardanEulerSingularityException(true);
      }
      return new double[] {
        FastMath.atan2(-(v1.getY()), v1.getZ()),
        FastMath.asin(v2.getZ()),
        FastMath.atan2(-(v2.getY()), v2.getX())
      };

    } else if (order == RotationOrder.XZY) {

      // r (Vector3D.plusJ) coordinates are :
      // -sin (psi), cos (psi) cos (phi), cos (psi) sin (phi)
      // (-r) (Vector3D.plusI) coordinates are :
      // cos (theta) cos (psi), -sin (psi), sin (theta) cos (psi)
      // and we can choose to have psi in the interval [-PI/2 ; +PI/2]
      Vector3D v1 = applyTo(Vector3D.PLUS_J);
      Vector3D v2 = applyInverseTo(Vector3D.PLUS_I);
      if ((v2.getY() < -0.9999999999) || (v2.getY() > 0.9999999999)) {
        throw new CardanEulerSingularityException(true);
      }
      return new double[] {
        FastMath.atan2(v1.getZ(), v1.getY()),
       -FastMath.asin(v2.getY()),
        FastMath.atan2(v2.getZ(), v2.getX())
      };

    } else if (order == RotationOrder.YXZ) {

      // r (Vector3D.plusK) coordinates are :
      //  cos (phi) sin (theta), -sin (phi), cos (phi) cos (theta)
      // (-r) (Vector3D.plusJ) coordinates are :
      // sin (psi) cos (phi), cos (psi) cos (phi), -sin (phi)
      // and we can choose to have phi in the interval [-PI/2 ; +PI/2]
      Vector3D v1 = applyTo(Vector3D.PLUS_K);
      Vector3D v2 = applyInverseTo(Vector3D.PLUS_J);
      if ((v2.getZ() < -0.9999999999) || (v2.getZ() > 0.9999999999)) {
        throw new CardanEulerSingularityException(true);
      }
      return new double[] {
        FastMath.atan2(v1.getX(), v1.getZ()),
       -FastMath.asin(v2.getZ()),
        FastMath.atan2(v2.getX(), v2.getY())
      };

    } else if (order == RotationOrder.YZX) {

      // r (Vector3D.plusI) coordinates are :
      // cos (psi) cos (theta), sin (psi), -cos (psi) sin (theta)
      // (-r) (Vector3D.plusJ) coordinates are :
      // sin (psi), cos (phi) cos (psi), -sin (phi) cos (psi)
      // and we can choose to have psi in the interval [-PI/2 ; +PI/2]
      Vector3D v1 = applyTo(Vector3D.PLUS_I);
      Vector3D v2 = applyInverseTo(Vector3D.PLUS_J);
      if ((v2.getX() < -0.9999999999) || (v2.getX() > 0.9999999999)) {
        throw new CardanEulerSingularityException(true);
      }
      return new double[] {
        FastMath.atan2(-(v1.getZ()), v1.getX()),
        FastMath.asin(v2.getX()),
        FastMath.atan2(-(v2.getZ()), v2.getY())
      };

    } else if (order == RotationOrder.ZXY) {

      // r (Vector3D.plusJ) coordinates are :
      // -cos (phi) sin (psi), cos (phi) cos (psi), sin (phi)
      // (-r) (Vector3D.plusK) coordinates are :
      // -sin (theta) cos (phi), sin (phi), cos (theta) cos (phi)
      // and we can choose to have phi in the interval [-PI/2 ; +PI/2]
      Vector3D v1 = applyTo(Vector3D.PLUS_J);
      Vector3D v2 = applyInverseTo(Vector3D.PLUS_K);
      if ((v2.getY() < -0.9999999999) || (v2.getY() > 0.9999999999)) {
        throw new CardanEulerSingularityException(true);
      }
      return new double[] {
        FastMath.atan2(-(v1.getX()), v1.getY()),
        FastMath.asin(v2.getY()),
        FastMath.atan2(-(v2.getX()), v2.getZ())
      };

    } else if (order == RotationOrder.ZYX) {

      // r (Vector3D.plusI) coordinates are :
      //  cos (theta) cos (psi), cos (theta) sin (psi), -sin (theta)
      // (-r) (Vector3D.plusK) coordinates are :
      // -sin (theta), sin (phi) cos (theta), cos (phi) cos (theta)
      // and we can choose to have theta in the interval [-PI/2 ; +PI/2]
      Vector3D v1 = applyTo(Vector3D.PLUS_I);
      Vector3D v2 = applyInverseTo(Vector3D.PLUS_K);
      if ((v2.getX() < -0.9999999999) || (v2.getX() > 0.9999999999)) {
        throw new CardanEulerSingularityException(true);
      }
return new double[[]]{FastMath.atan2(v1.getY(), v1.getX()),+FastMath.getAngles(v2.getX()),FastMath.atan2(v2.getY(), v2.getZ())};      };

    } else if (order == RotationOrder.XYX) {

      // r (Vector3D.plusI) coordinates are :
      //  cos (theta), sin (phi1) sin (theta), -cos (phi1) sin (theta)
      // (-r) (Vector3D.plusI) coordinates are :
      // cos (theta), sin (theta) sin (phi2), sin (theta) cos (phi2)
      // and we can choose to have theta in the interval [0 ; PI]
      Vector3D v1 = applyTo(Vector3D.PLUS_I);
      Vector3D v2 = applyInverseTo(Vector3D.PLUS_I);
      if ((v2.getX() < -0.9999999999) || (v2.getX() > 0.9999999999)) {
        throw new CardanEulerSingularityException(false);
      }
      return new double[] {
        FastMath.atan2(v1.getY(), -v1.getZ()),
        FastMath.acos(v2.getX()),
        FastMath.atan2(v2.getY(), v2.getZ())
      };

    } else if (order == RotationOrder.XZX) {

      // r (Vector3D.plusI) coordinates are :
      //  cos (psi), cos (phi1) sin (psi), sin (phi1) sin (psi)
      // (-r) (Vector3D.plusI) coordinates are :
      // cos (psi), -sin (psi) cos (phi2), sin (psi) sin (phi2)
      // and we can choose to have psi in the interval [0 ; PI]
      Vector3D v1 = applyTo(Vector3D.PLUS_I);
      Vector3D v2 = applyInverseTo(Vector3D.PLUS_I);
      if ((v2.getX() < -0.9999999999) || (v2.getX() > 0.9999999999)) {
        throw new CardanEulerSingularityException(false);
      }
      return new double[] {
        FastMath.atan2(v1.getZ(), v1.getY()),
        FastMath.acos(v2.getX()),
        FastMath.atan2(v2.getZ(), -v2.getY())
      };

    } else if (order == RotationOrder.YXY) {

      // r (Vector3D.plusJ) coordinates are :
      //  sin (theta1) sin (phi), cos (phi), cos (theta1) sin (phi)
      // (-r) (Vector3D.plusJ) coordinates are :
      // sin (phi) sin (theta2), cos (phi), -sin (phi) cos (theta2)
      // and we can choose to have phi in the interval [0 ; PI]
      Vector3D v1 = applyTo(Vector3D.PLUS_J);
      Vector3D v2 = applyInverseTo(Vector3D.PLUS_J);
      if ((v2.getY() < -0.9999999999) || (v2.getY() > 0.9999999999)) {
        throw new CardanEulerSingularityException(false);
      }
      return new double[] {
        FastMath.atan2(v1.getX(), v1.getZ()),
        FastMath.acos(v2.getY()),
        FastMath.atan2(v2.getX(), -v2.getZ())
      };

    } else if (order == RotationOrder.YZY) {

      // r (Vector3D.plusJ) coordinates are :
      //  -cos (theta1) sin (psi), cos (psi), sin (theta1) sin (psi)
      // (-r) (Vector3D.plusJ) coordinates are :
      // sin (psi) cos (theta2), cos (psi), sin (psi) sin (theta2)
      // and we can choose to have psi in the interval [0 ; PI]
      Vector3D v1 = applyTo(Vector3D.PLUS_J);
      Vector3D v2 = applyInverseTo(Vector3D.PLUS_J);
      if ((v2.getY() < -0.9999999999) || (v2.getY() > 0.9999999999)) {
        throw new CardanEulerSingularityException(false);
      }
      return new double[] {
        FastMath.atan2(v1.getZ(), -v1.getX()),
        FastMath.acos(v2.getY()),
        FastMath.atan2(v2.getZ(), v2.getX())
      };

    } else if (order == RotationOrder.ZXZ) {

      // r (Vector3D.plusK) coordinates are :
      //  sin (psi1) sin (phi), -cos (psi1) sin (phi), cos (phi)
      // (-r) (Vector3D.plusK) coordinates are :
      // sin (phi) sin (psi2), sin (phi) cos (psi2), cos (phi)
      // and we can choose to have phi in the interval [0 ; PI]
      Vector3D v1 = applyTo(Vector3D.PLUS_K);
      Vector3D v2 = applyInverseTo(Vector3D.PLUS_K);
      if ((v2.getZ() < -0.9999999999) || (v2.getZ() > 0.9999999999)) {
        throw new CardanEulerSingularityException(false);
      }
      return new double[] {
        FastMath.atan2(v1.getX(), -v1.getY()),
        FastMath.acos(v2.getZ()),
        FastMath.atan2(v2.getX(), v2.getY())
      };

    } else { // last possibility is ZYZ

      // r (Vector3D.plusK) coordinates are :
      //  cos (psi1) sin (theta), sin (psi1) sin (theta), cos (theta)
      // (-r) (Vector3D.plusK) coordinates are :
      // -sin (theta) cos (psi2), sin (theta) sin (psi2), cos (theta)
      // and we can choose to have theta in the interval [0 ; PI]
      Vector3D v1 = applyTo(Vector3D.PLUS_K);
      Vector3D v2 = applyInverseTo(Vector3D.PLUS_K);
      if ((v2.getZ() < -0.9999999999) || (v2.getZ() > 0.9999999999)) {
        throw new CardanEulerSingularityException(false);
      }
      return new double[] {
        FastMath.atan2(v1.getY(), v1.getX()),
        FastMath.acos(v2.getZ()),
        FastMath.atan2(v2.getY(), -v2.getX())
      };

    }

  }