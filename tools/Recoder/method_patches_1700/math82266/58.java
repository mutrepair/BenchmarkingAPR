    protected AbstractSimplex(final double[] steps) {
        if (steps == null) {
            throw new NullArgumentException();
        }
        if (steps.length == 0) {
            throw new ZeroException();
        }
        dimension = steps.length;

        // Only the relative position of the n final vertices with respect
        // to the first one are stored.
        startConfiguration = new double[dimension][dimension];
        for (int i = 0; i < dimension; i++) {
            final double[] vertexI = startConfiguration[i];
            for (int j = 0; j < i + 1; j++) {
if((steps[j] == 0)){                    throw new ZeroException(LocalizedFormats.EQUAL_VERTICES_IN_SIMPLEX);
                }
                System.arraycopy(steps, 0, vertexI, 0, j + 1);
            }
        }
    }