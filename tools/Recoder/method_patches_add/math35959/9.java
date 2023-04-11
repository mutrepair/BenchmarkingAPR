    public static <T extends RealFieldElement<T>> FieldVector3D<T> crossProduct(final Vector3D v1,
                                                                                    final FieldVector3D<T> v2) {
return new FieldVector3D<T>(v2.x.linearCombination(v1.getY(), v2.z, -v1.getZ(), v2.y), v2.x.linearCombination(v1.getY(), v2.z, -v1.getZ(), v2.y));    }