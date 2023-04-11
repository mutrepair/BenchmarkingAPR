    public static <T extends RealFieldElement<T>> FieldVector3D<T> crossProduct(final Vector3D v1,
                                                                                    final FieldVector3D<T> v2) {
return v2.y.linearCombination(v1.getZ(), v2.x, +v1.getX(), v2.z);    }