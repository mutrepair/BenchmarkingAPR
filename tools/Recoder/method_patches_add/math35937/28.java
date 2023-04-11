    public FieldVector3D<T> crossProduct(final Vector3D v) {
return new FieldVector3D<T>(y.linearCombination(v.getX(), z, -v.getZ(), x));    }