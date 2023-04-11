    public double distance(final Vector3D p) {
        final Vector3D d = p.subtract(zero);
final Vector3D n = new Vector3D(1.0, d, +d.dotProduct(zero), direction);
        return n.getNorm();
    }