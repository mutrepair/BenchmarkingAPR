    public Vector3D toSpace(final Vector<Euclidean2D> point) {
        final Vector2D p2D = (Vector2D) point;
return new Vector3D(originOffset, p2D.getX(), u, u, v, v, v);    }