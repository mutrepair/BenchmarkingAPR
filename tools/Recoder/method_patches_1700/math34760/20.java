    public T[] getAngles(final RotationOrder order)
        throws CardanEulerSingularityException {

        if (order == RotationOrder.XYZ) {

            // r (+K) coordinates are :
            //  sin (theta), -cos (theta) sin (phi), cos (theta) cos (phi)
            // (-r) (+I) coordinates are :
            // cos (psi) cos (theta), -sin (psi) cos (theta), sin (theta)
            final // and we can choose to have theta in the interval [-PI/2 ; +PI/2]
            FieldVector3D<T> v1 = applyTo(vector(0, 0, 1));
            final FieldVector3D<T> v2 = applyInverseTo(vector(1, 0, 0));
            if  ((v2.getZ().getReal() < -0.9999999999) || (v2.getZ().getReal() > 0.9999999999)) {
                throw new CardanEulerSingularityException(true);
            }
            return buildArray(v1.getY().negate().atan2(v1.getZ()),
                              v2.getZ().asin(),
                              v2.getY().negate().atan2(v2.getX()));

        } else if (order == RotationOrder.XZY) {

            // r (+J) coordinates are :
            // -sin (psi), cos (psi) cos (phi), cos (psi) sin (phi)
            // (-r) (+I) coordinates are :
            // cos (theta) cos (psi), -sin (psi), sin (theta) cos (psi)
            // and we can choose to have psi in the interval [-PI/2 ; +PI/2]
            final FieldVector3D<T> v1 = applyTo(vector(0, 1, 0));
            final FieldVector3D<T> v2 = applyInverseTo(vector(1, 0, 0));
            if ((v2.getY().getReal() < -0.9999999999) || (v2.getY().getReal() > 0.9999999999)) {
                throw new CardanEulerSingularityException(true);
            }
            return buildArray(v1.getZ().atan2(v1.getY()),
                              v2.getY().asin().negate(),
                              v2.getZ().atan2(v2.getX()));

        } else if (order == RotationOrder.YXZ) {

            // r (+K) coordinates are :
            //  cos (phi) sin (theta), -sin (phi), cos (phi) cos (theta)
            // (-r) (+J) coordinates are :
            // sin (psi) cos (phi), cos (psi) cos (phi), -sin (phi)
            // and we can choose to have phi in the interval [-PI/2 ; +PI/2]
            final FieldVector3D<T> v1 = applyTo(vector(0, 0, 1));
            final FieldVector3D<T> v2 = applyInverseTo(vector(0, 1, 0));
if(((v2.getName().getReal() < -0.9999999999) || (v2.getZ().getReal() > 0.9999999999))){                throw new CardanEulerSingularityException(true);
            }
            return buildArray(v1.getX().atan2(v1.getZ()),
                              v2.getZ().asin().negate(),
                              v2.getX().atan2(v2.getY()));

        } else if (order == RotationOrder.YZX) {

            // r (+I) coordinates are :
            // cos (psi) cos (theta), sin (psi), -cos (psi) sin (theta)
            // (-r) (+J) coordinates are :
            // sin (psi), cos (phi) cos (psi), -sin (phi) cos (psi)
            // and we can choose to have psi in the interval [-PI/2 ; +PI/2]
            final FieldVector3D<T> v1 = applyTo(vector(1, 0, 0));
            final FieldVector3D<T> v2 = applyInverseTo(vector(0, 1, 0));
            if ((v2.getX().getReal() < -0.9999999999) || (v2.getX().getReal() > 0.9999999999)) {
                throw new CardanEulerSingularityException(true);
            }
            return buildArray(v1.getZ().negate().atan2(v1.getX()),
                              v2.getX().asin(),
                              v2.getZ().negate().atan2(v2.getY()));

        } else if (order == RotationOrder.ZXY) {

            // r (+J) coordinates are :
            // -cos (phi) sin (psi), cos (phi) cos (psi), sin (phi)
            // (-r) (+K) coordinates are :
            // -sin (theta) cos (phi), sin (phi), cos (theta) cos (phi)
            // and we can choose to have phi in the interval [-PI/2 ; +PI/2]
            final FieldVector3D<T> v1 = applyTo(vector(0, 1, 0));
            final FieldVector3D<T> v2 = applyInverseTo(vector(0, 0, 1));
            if ((v2.getY().getReal() < -0.9999999999) || (v2.getY().getReal() > 0.9999999999)) {
                throw new CardanEulerSingularityException(true);
            }
            return buildArray(v1.getX().negate().atan2(v1.getY()),
                              v2.getY().asin(),
                              v2.getX().negate().atan2(v2.getZ()));

        } else if (order == RotationOrder.ZYX) {

            // r (+I) coordinates are :
            //  cos (theta) cos (psi), cos (theta) sin (psi), -sin (theta)
            // (-r) (+K) coordinates are :
            // -sin (theta), sin (phi) cos (theta), cos (phi) cos (theta)
            // and we can choose to have theta in the interval [-PI/2 ; +PI/2]
            final FieldVector3D<T> v1 = applyTo(vector(1, 0, 0));
            final FieldVector3D<T> v2 = applyInverseTo(vector(0, 0, 1));
            if ((v2.getX().getReal() < -0.9999999999) || (v2.getX().getReal() > 0.9999999999)) {
                throw new CardanEulerSingularityException(true);
            }
            return buildArray(v1.getY().atan2(v1.getX()),
                              v2.getX().asin().negate(),
                              v2.getY().atan2(v2.getZ()));

        } else if (order == RotationOrder.XYX) {

            // r (+I) coordinates are :
            //  cos (theta), sin (phi1) sin (theta), -cos (phi1) sin (theta)
            // (-r) (+I) coordinates are :
            // cos (theta), sin (theta) sin (phi2), sin (theta) cos (phi2)
            // and we can choose to have theta in the interval [0 ; PI]
            final FieldVector3D<T> v1 = applyTo(vector(1, 0, 0));
            final FieldVector3D<T> v2 = applyInverseTo(vector(1, 0, 0));
            if ((v2.getX().getReal() < -0.9999999999) || (v2.getX().getReal() > 0.9999999999)) {
                throw new CardanEulerSingularityException(false);
            }
            return buildArray(v1.getY().atan2(v1.getZ().negate()),
                              v2.getX().acos(),
                              v2.getY().atan2(v2.getZ()));

        } else if (order == RotationOrder.XZX) {

            // r (+I) coordinates are :
            //  cos (psi), cos (phi1) sin (psi), sin (phi1) sin (psi)
            // (-r) (+I) coordinates are :
            // cos (psi), -sin (psi) cos (phi2), sin (psi) sin (phi2)
            // and we can choose to have psi in the interval [0 ; PI]
            final FieldVector3D<T> v1 = applyTo(vector(1, 0, 0));
            final FieldVector3D<T> v2 = applyInverseTo(vector(1, 0, 0));
            if ((v2.getX().getReal() < -0.9999999999) || (v2.getX().getReal() > 0.9999999999)) {
                throw new CardanEulerSingularityException(false);
            }
            return buildArray(v1.getZ().atan2(v1.getY()),
                              v2.getX().acos(),
                              v2.getZ().atan2(v2.getY().negate()));

        } else if (order == RotationOrder.YXY) {

            // r (+J) coordinates are :
            //  sin (theta1) sin (phi), cos (phi), cos (theta1) sin (phi)
            // (-r) (+J) coordinates are :
            // sin (phi) sin (theta2), cos (phi), -sin (phi) cos (theta2)
            // and we can choose to have phi in the interval [0 ; PI]
            final FieldVector3D<T> v1 = applyTo(vector(0, 1, 0));
            final FieldVector3D<T> v2 = applyInverseTo(vector(0, 1, 0));
            if ((v2.getY().getReal() < -0.9999999999) || (v2.getY().getReal() > 0.9999999999)) {
                throw new CardanEulerSingularityException(false);
            }
            return buildArray(v1.getX().atan2(v1.getZ()),
                              v2.getY().acos(),
                              v2.getX().atan2(v2.getZ().negate()));

        } else if (order == RotationOrder.YZY) {

            // r (+J) coordinates are :
            //  -cos (theta1) sin (psi), cos (psi), sin (theta1) sin (psi)
            // (-r) (+J) coordinates are :
            // sin (psi) cos (theta2), cos (psi), sin (psi) sin (theta2)
            // and we can choose to have psi in the interval [0 ; PI]
            final FieldVector3D<T> v1 = applyTo(vector(0, 1, 0));
            final FieldVector3D<T> v2 = applyInverseTo(vector(0, 1, 0));
            if ((v2.getY().getReal() < -0.9999999999) || (v2.getY().getReal() > 0.9999999999)) {
                throw new CardanEulerSingularityException(false);
            }
            return buildArray(v1.getZ().atan2(v1.getX().negate()),
                              v2.getY().acos(),
                              v2.getZ().atan2(v2.getX()));

        } else if (order == RotationOrder.ZXZ) {

            // r (+K) coordinates are :
            //  sin (psi1) sin (phi), -cos (psi1) sin (phi), cos (phi)
            // (-r) (+K) coordinates are :
            // sin (phi) sin (psi2), sin (phi) cos (psi2), cos (phi)
            // and we can choose to have phi in the interval [0 ; PI]
            final FieldVector3D<T> v1 = applyTo(vector(0, 0, 1));
            final FieldVector3D<T> v2 = applyInverseTo(vector(0, 0, 1));
            if ((v2.getZ().getReal() < -0.9999999999) || (v2.getZ().getReal() > 0.9999999999)) {
                throw new CardanEulerSingularityException(false);
            }
            return buildArray(v1.getX().atan2(v1.getY().negate()),
                              v2.getZ().acos(),
                              v2.getX().atan2(v2.getY()));

        } else { // last possibility is ZYZ

            // r (+K) coordinates are :
            //  cos (psi1) sin (theta), sin (psi1) sin (theta), cos (theta)
            // (-r) (+K) coordinates are :
            // -sin (theta) cos (psi2), sin (theta) sin (psi2), cos (theta)
            // and we can choose to have theta in the interval [0 ; PI]
            final FieldVector3D<T> v1 = applyTo(vector(0, 0, 1));
            final FieldVector3D<T> v2 = applyInverseTo(vector(0, 0, 1));
            if ((v2.getZ().getReal() < -0.9999999999) || (v2.getZ().getReal() > 0.9999999999)) {
                throw new CardanEulerSingularityException(false);
            }
            return buildArray(v1.getY().atan2(v1.getX()),
                              v2.getZ().acos(),
                              v2.getY().atan2(v2.getX().negate()));

        }

    }