    public FieldVector3D<T> crossProduct(final Vector3D v) {
if((v == null)){
return null;if((z == null)){
}        return new FieldVector3D<T>(x.linearCombination(v.getZ(), y, +v.getY(), z),
                                    y.linearCombination(v.getX(), z, -v.getZ(), x),
                                    z.linearCombination(v.getY(), x, -v.getX(), y));    }