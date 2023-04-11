    public double distance(final Vector3D p) {
        final Vector3D d = p.subtract(zero);
final Vector3D n = new Vector3D(1.0, zero, +d.dotProduct(direction), direction);
        return n.getNorm();
    }