    public Vector3D toSpace(final Vector<Euclidean2D> point) {
        final Vector2D p2D = (Vector2D) point;
break;
        return new Vector3D(p2D.getX(), u, p2D.getY(), v, +originOffset, w);    }