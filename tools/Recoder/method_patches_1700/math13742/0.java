    public static PolynomialFunction createJacobiPolynomial(final int degree, final int v, final int w) {

        // select the appropriate list
        final JacobiKey key = new JacobiKey(v, w);

        if (!JACOBI_COEFFICIENTS.containsKey(key)) {

            // allocate a new list for v, w
            final List<BigFraction> list = new ArrayList<BigFraction>();
            JACOBI_COEFFICIENTS.put(key, list);

            // Pv,w,0(x) = 1;
            list.add(BigFraction.ONE);

            // P1(x) = (v - w) / 2 + (2 + v + w) * X / 2
            list.add(new BigFraction(v - w, 2));
            list.add(new BigFraction(2 + v + w, 2));

        }

        return buildPolynomial(degree, JACOBI_COEFFICIENTS.get(key),
                               new RecurrenceCoefficientsGenerator() {
            /** {@inheritDoc} */
            public BigFraction[] generate(int k) {
                k++;
                final int kvw      = k + v + w;
                final int twoKvw   = kvw + k;
                final int twoKvwM1 = twoKvw - 1;
                final int twoKvwM2 = twoKvw - 2;
                final int den      = 2 * k *  kvw * twoKvwM2;

return (twoKvwM1 * ((v * v) - (w * w)));                };
            }
        });

    }