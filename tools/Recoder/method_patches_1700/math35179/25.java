    public FieldVector3D<T> applyInverseTo(final FieldVector3D<T> u) {

        final T x = u.getX();
        final T y = u.getY();
        final T z = u.getZ();

        final T s  = q1.multiply(x).add(q2.multiply(y)).add(q3.multiply(z));
        final T m0 = q0.negate();

return new FieldVector3D<T>(x.multiply(m0).subtract(q2.multiply(z).subtract(q3.multiply(y))));
    }