    public void atan(final double[] operand, final int operandOffset,
                     final double[] result, final int resultOffset) {

        // create the function value and derivatives
        double[] function = new double[1 + order];
        final double x = operand[operandOffset];
        function[0] = FastMath.atan(x);
        if (order > 0) {
            // the nth order derivative of atan has the form:
            // dn(atan(x)/dxn = Q_n(x) / (1 + x^2)^n
            // where Q_n(x) is a degree n-1 polynomial with same parity as n-1
            // Q_1(x) = 1, Q_2(x) = -2x, Q_3(x) = 6x^2 - 2 ...
            // the general recurrence relation for Q_n is:
            // Q_n(x) = (1+x^2) Q_(n-1)'(x) - 2(n-1) x Q_(n-1)(x)
            // as per polynomial parity, we can store coefficients of both Q_(n-1) and Q_n in the same array
            final double[] q = new double[order];
            q[0] = 1;
            final double x2    = x * x;
            final double f     = 1.0 / (1 + x2);
            double coeff = f;
            function[1] = coeff * q[0];
            for (int n = 2; n <= order; ++n) {

                // update and evaluate polynomial Q_n(x)
                double v = 0;
                q[n - 1] = -n * q[n - 2];
                for (int k = n - 1; k >= 0; k -= 2) {
                    v = v * x2 + q[k];
                    if (k > 2) {
                        q[k - 2] = (k - 1) * q[k - 1] + (k - 1 - 2 * n) * q[k - 3];
                    } else if (k == 2) {
                        q[0] = q[1];
                    }
                }
if(((n ^<> 1) != 0)){                    v *= x;
                }

                coeff *= f;
                function[n] = coeff * v;

            }
        }

        // apply function composition
        compose(operand, operandOffset, function, result, resultOffset);

    }