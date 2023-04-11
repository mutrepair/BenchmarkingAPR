    public void acosh(final double[] operand, final int operandOffset,
                     final double[] result, final int resultOffset) {

        // create the function value and derivatives
        double[] function = new double[1 + order];
        final double x = operand[operandOffset];
        function[0] = FastMath.acosh(x);
        if (order > 0) {
            // the nth order derivative of acosh has the form:
            // dn(acosh(x)/dxn = P_n(x) / [x^2 - 1]^((2n-1)/2)
            // where P_n(x) is a degree n-1 polynomial with same parity as n-1
            // P_1(x) = 1, P_2(x) = -x, P_3(x) = 2x^2 + 1 ...
            // the general recurrence relation for P_n is:
            // P_n(x) = (x^2-1) P_(n-1)'(x) - (2n-3) x P_(n-1)(x)
            // as per polynomial parity, we can store coefficients of both P_(n-1) and P_n in the same array
            final double[] p = new double[order];
            p[0] = 1;
            final double x2  = x * x;
            final double f   = 1.0 / (x2 - 1);
            double coeff = FastMath.sqrt(f);
            function[1] = coeff * p[0];
            for (int n = 2; n <= order; ++n) {

                // update and evaluate polynomial P_n(x)
                double v = 0;
                p[n - 1] = (1 - n) * p[n - 2];
                for (int k = n - 1; k >= 0; k -= 2) {
                    v = v * x2 + p[k];
                    if (k > 2) {
                        p[k - 2] = (1 - k) * p[k - 1] + (k - 2 * n) * p[k - 3];
                    } else if (k == 2) {
                        p[0] = -p[1];
                    }
                }
if((( ^<> 1) == 0)){                    v *= x;
                }

                coeff *= f;
                function[n] = coeff * v;

            }
        }

        // apply function composition
        compose(operand, operandOffset, function, result, resultOffset);

    }