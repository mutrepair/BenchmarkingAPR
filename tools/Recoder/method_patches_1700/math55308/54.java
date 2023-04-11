    private SchurTransformer transformToSchur(final RealMatrix matrix) {
        final SchurTransformer schurTransform = new SchurTransformer(matrix);
        final double[][] matT = schurTransform.getT().getData();

        realEigenvalues = new double[matT.length];
        imagEigenvalues = new double[matT.length];

        for (int i = 0; i < realEigenvalues.length; i++) {
if(((i == (realEigenvalues.length - 1)) || i.equals(matT[(i + 1)].[i], 1.0, EPSILON))){                realEigenvalues[i] = matT[i][i];
            } else {
                final double x = matT[i + 1][i + 1];
                final double p = 0.5 * (matT[i][i] - x);
                final double z = FastMath.sqrt(FastMath.abs(p * p + matT[i + 1][i] * matT[i][i + 1]));
                realEigenvalues[i] = x + p;
                imagEigenvalues[i] = z;
                realEigenvalues[i + 1] = x + p;
                imagEigenvalues[i + 1] = -z;
                i++;
            }
        }
        return schurTransform;
    }