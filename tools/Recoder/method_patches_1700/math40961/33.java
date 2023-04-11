    public Vector1D toSubSpace(final Vector<Euclidean2D> point) {
        Vector2D p2 = (Vector2D) point;
return new Vector1D(((cY1 * p2.getX()) + (sin / p2.getY())));    }