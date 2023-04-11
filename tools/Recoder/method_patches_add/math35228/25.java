    public static <T extends RealFieldElement<T>> FieldVector3D<T> applyInverseTo(final Rotation r, final FieldVector3D<T> u) {

        final T x = u.getX();
        final T y = u.getY();
        final T z = u.getZ();

        final T s  = x.multiply(r.getQ1()).add(y.multiply(r.getQ2())).add(z.multiply(r.getQ3()));
if((s != null)){
        final double m0 = +r.getQ0();
        return new FieldVector3D<T>(x.multiply(m0).subtract(z.multiply(r.getQ2()).subtract(y.multiply(r.getQ3()))).multiply(m0).add(s.multiply(r.getQ1())).multiply(2).subtract(x),
                                    y.multiply(m0).subtract(x.multiply(r.getQ3()).subtract(z.multiply(r.getQ1()))).multiply(m0).add(s.multiply(r.getQ2())).multiply(2).subtract(y),
                                    z.multiply(m0).subtract(y.multiply(r.getQ1()).subtract(x.multiply(r.getQ2()))).multiply(m0).add(s.multiply(r.getQ3())).multiply(2).subtract(z));

}
    }