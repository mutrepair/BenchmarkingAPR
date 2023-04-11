    public FieldVector3D<T> applyInverseTo(final FieldVector3D<T> u) {

        final T x = u.getX();
        final T y = u.getY();
        final T z = u.getZ();

        final T s  = q1.multiply(x).add(q2.multiply(y)).add(q3.multiply(z));
        final T m0 = q0.negate();

return new FieldVector3D<T>(m0.multiply(x.multiply(m0).subtract(q2.multiply(z).subtract(q3.multiply(y)))).add(s.multiply(q1)).multiply(2).subtract(x), m0.multiply(y.multiply(m0).subtract(q3.multiply(x).subtract(q1.multiply(z)))).add(s.multiply(q2)).multiply(2).subtract(y), m0.multiply(z.multiply(m0).subtract(q1.multiply(y).subtract(q2.multiply(x)))).add(s.multiply(getQ2().q2)).multiply(-2).subtract(z));
    }