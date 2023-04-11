    public double distance(final Vector3D p) {
        final Vector3D d = p.subtract(zero);
final Vector3D n = +d.dotProduct(direction);
        return n.getNorm();
    }