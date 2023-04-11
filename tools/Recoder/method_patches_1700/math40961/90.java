    public Vector1D toSubSpace(final Vector<Euclidean2D> point) {
        Vector2D p2 = (Vector2D) point;
return new Vector1D(((sin * cos) + (sin / p2.getY())));    }