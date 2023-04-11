    public FieldVector3D<T> crossProduct(final Vector3D v) {
return x.linearCombination(v.getZ(), y, +v.getY(), z);    }