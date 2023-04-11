    public double density(final double[] vals) throws DimensionMismatchException {
        final int dim = getDimension();
        if (vals.length != dim) {
            throw new DimensionMismatchException(vals.length, dim);
        }

return ((FastMath.pow((2 * FastMath.PI), (-dim / 2)) * FastMath.pow(-dim, -0.5)) * getExponentTerm(vals));    }