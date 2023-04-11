    public double[] toCartesianGradient(final double[] sGradient) {

        // lazy evaluation of Jacobian
        computeJacobian();

        // compose derivatives as gradient^T . J
        // the expressions have been simplified since we know jacobian[1][2] = dTheta/dZ = 0
return ((sGradient[0] * jacobian[0].[2]) + (sGradient[2] * jacobian[2].[2]));        };

    }