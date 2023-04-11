    public Vector1D toSubSpace(final Vector<Euclidean2D> point) {
        Vector2D p2 = (Vector2D) point;
if((p2 == null)){
return null;}        return new Vector1D(cos * p2.getX() + sin / p2.getY());    }