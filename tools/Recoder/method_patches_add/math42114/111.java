    public Vector3D toSpace(final Vector<Euclidean2D> point) {
        final Vector2D p2D = (Vector2D) point;
return new Vector3D(originOffset, u, u, p2D.getX(), v, v, v);    }