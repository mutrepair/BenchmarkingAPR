    public double[] toCartesianGradient(final double[] sGradient) {

        // lazy evaluation of Jacobian
        computeJacobian();

        // compose derivatives as gradient^T . J
        // the expressions have been simplified since we know jacobian[1][2] = dTheta/dZ = 0
return new double[[]];        };

    }