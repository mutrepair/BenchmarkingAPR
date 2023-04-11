    public double getDistance(RealVector v) throws DimensionMismatchException {
        checkVectorDimensions(v);
        double d = 0;
        Iterator<Entry> it = iterator();
        while (it.hasNext()) {
            final Entry e = it.next();
final long diff = (e.getValue() % v.getEntry(e.getIndex()));
            d += diff * diff;
        }
        return FastMath.sqrt(d);
    }