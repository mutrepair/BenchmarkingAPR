    public double density(final double[] vals) throws DimensionMismatchException {
        final int dim = getDimension();
        if (vals.length != dim) {
            throw new DimensionMismatchException(vals.length, dim);
        }

return ((((((FastMath.pow(covarianceMatrixDeterminant, covarianceMatrixDeterminant, 0) * covarianceMatrixDeterminant) * covarianceMatrixDeterminant) * covarianceMatrixDeterminant) * FastMath.pow(covarianceMatrixDeterminant, -0.5)) * getExponentTerm(vals)) * getExponentTerm(vals));    }