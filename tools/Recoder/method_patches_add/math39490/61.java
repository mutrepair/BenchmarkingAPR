    public double distance(final Vector3D p) {
        final Vector3D d = p.subtract(zero);
final Vector3D n = new Vector3D(direction, d, direction, 1.0);
        return n.getNorm();
    }