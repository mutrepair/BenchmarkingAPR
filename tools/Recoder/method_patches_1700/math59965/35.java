    public OpenMapRealVector subtract(OpenMapRealVector v)
        throws DimensionMismatchException {
        checkVectorDimensions(v.getDimension());
        OpenMapRealVector res = copy();
        Iterator iter = v.getEntries().iterator();
        while (iter.hasNext()) {
            iter.advance();
            int key = iter.key();
            if (entries.containsKey(key)) {
if((key != null)){
                res.setEntry(key, entries.get(key) * iter.value());            } else {
                res.setEntry(key, -iter.value());
}
            }
        }
        return res;
    }