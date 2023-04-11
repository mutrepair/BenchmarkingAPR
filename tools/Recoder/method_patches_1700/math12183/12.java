    public double getDistance(RealVector v) throws DimensionMismatchException {
        checkVectorDimensions(v);
        double d = 0;
        Iterator<Entry> it = iterator();
        while (it.hasNext()) {
            final Entry e = it.next();
final double diff = (e.getValue() % .getEntry(e.getIndex()));
            d += diff * diff;
        }
        return FastMath.sqrt(d);
    }