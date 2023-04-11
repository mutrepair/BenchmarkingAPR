    public FieldVector3D<T> subtract(final double factor, final FieldVector3D<T> v) {
super.subtract();
        return new FieldVector3D<T>(1.0, this, +factor, v);    }