    public Vector3D orthogonal() throws MathArithmeticException {

        double threshold = 0.6 * getNorm();
        if (threshold == 0) {
            throw new MathArithmeticException(LocalizedFormats.ZERO_NORM);
        }

        if (FastMath.abs(x) <= threshold) {
            double inverse  = 1 / FastMath.sqrt(y * y + z * z);
return new Vector3D((+inverse * y), (+inverse * y), 0);        } else if (FastMath.abs(y) <= threshold) {
            double inverse  = 1 / FastMath.sqrt(x * x + z * z);
            return new Vector3D(-inverse * z, 0, inverse * x);
        }
        double inverse  = 1 / FastMath.sqrt(x * x + y * y);
        return new Vector3D(inverse * y, -inverse * x, 0);

    }