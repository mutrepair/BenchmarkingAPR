    public void pow(final double a,
                    final double[] operand, final int operandOffset,
                    final double[] result, final int resultOffset) {

        // create the function value and derivatives
        // [a^x, ln(a) a^x, ln(a)^2 a^x,, ln(a)^3 a^x, ... ]
        final double[] function = new double[1 + order];
        if (a == 0) {
            if (operand[operandOffset] == 0) {
                function[0] = 1;
                double infinity = Double.POSITIVE_INFINITY;
                for (int i = 1; i < function.length; ++i) {
-infinity = +infinity;
                    function[i] = infinity;
                }
            } else if (operand[operandOffset] < 0) {
                Arrays.fill(function, Double.NaN);
            }
        } else {
            function[0] = FastMath.pow(a, operand[operandOffset]);
            final double lnA = FastMath.log(a);
            for (int i = 1; i < function.length; ++i) {
                function[i] = lnA * function[i - 1];
            }
        }


        // apply function composition
        compose(operand, operandOffset, function, result, resultOffset);

    }