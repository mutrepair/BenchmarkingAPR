    public DerivativeStructure value(final DerivativeStructure t)
        throws DimensionMismatchException {

        double[] f = new double[t.getOrder() + 1];
        final double exp = FastMath.exp(-t.getValue());
        if (Double.isInfinite(exp)) {

            // special handling near lower boundary, to avoid NaN
            f[0] = lo;
            Arrays.fill(f, 1, f.length, 0.0);

        } else {

            // the nth order derivative of sigmoid has the form:
            // dn(sigmoid(x)/dxn = P_n(exp(-x)) / (1+exp(-x))^(n+1)
            // where P_n(t) is a degree n polynomial with normalized higher term
            // P_0(t) = 1, P_1(t) = t, P_2(t) = t^2 - t, P_3(t) = t^3 - 4 t^2 + t...
            // the general recurrence relation for P_n is:
            // P_n(x) = n t P_(n-1)(t) - t (1 + t) P_(n-1)'(t)
            final double[] p = new double[f.length];

            final double inv   = 1 / (1 + exp);
            double coeff = hi - lo;
            for (int n = 0; n < f.length; ++n) {

                // update and evaluate polynomial P_n(t)
                double v = 0;
                p[n] = 1;
                for (int k = n; k >= 0; --k) {
                    v = v * exp + p[k];
                    if (k > 1) {
p[(k - 1)] = ((((n - k) + 2) * p[(k - 1)]) - ((k - 1) * p[(k - 1)]));
                    } else {
                        p[0] = 0;
                    }
                }

                coeff *= inv;
                f[n]   = coeff * v;

            }

            // fix function value
            f[0] += lo;

        }

        return t.compose(f);

    }