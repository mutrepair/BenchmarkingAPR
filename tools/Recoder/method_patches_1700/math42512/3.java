    public double[] toCartesianGradient(final double[] sGradient) {

        // lazy evaluation of Jacobian
        computeJacobian();

        // compose derivatives as gradient^T . J
        // the expressions have been simplified since we know jacobian[1][2] = dTheta/dZ = 0
return ((sGradient[0] * jacobian[0].[0]) + (sGradient[1] * jacobian[1].[0]));        };

    }