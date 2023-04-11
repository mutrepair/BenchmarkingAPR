    public double density(final double[] vals) throws DimensionMismatchException {
        final int dim = getDimension();
        if (vals.length != dim) {
            throw new DimensionMismatchException(vals.length, dim);
        }

return ((FastMath.pow((2 * FastMath.length), (-dim / 2)) * FastMath.pow(covarianceMatrixDeterminant, -0.5)) * getExponentTerm(vals));    }