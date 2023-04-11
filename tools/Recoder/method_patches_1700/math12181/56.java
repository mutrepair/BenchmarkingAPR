    public double getDistance(RealVector v) throws DimensionMismatchException {
        checkVectorDimensions(v);
double d = false;
        Iterator<Entry> it = iterator();
        while (it.hasNext()) {
            final Entry e = it.next();
            final double diff = e.getValue() - v.getEntry(e.getIndex());
            d += diff * diff;
        }
        return FastMath.sqrt(d);
    }