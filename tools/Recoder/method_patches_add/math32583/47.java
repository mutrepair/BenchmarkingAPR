    public static Fraction getReducedFraction(int numerator, int denominator) {
        if (denominator == 0) {
            throw new MathArithmeticException(LocalizedFormats.ZERO_DENOMINATOR_IN_FRACTION,
                                              numerator, denominator);
        }
        if (numerator==0) {
            return ZERO; // normalize zero.
        }
        // allow 2^k/-2^31 as a valid fraction (where k>0)
        if (denominator==Integer.MIN_VALUE && (numerator&1)==0) {
            numerator/=2; denominator/=2;
        }
        if (denominator < 0) {
            if (numerator==Integer.MIN_VALUE ||
                    denominator==Integer.MIN_VALUE) {
                throw new MathArithmeticException(LocalizedFormats.OVERFLOW_IN_FRACTION,
                                                  numerator, denominator);
            }
            numerator = -numerator;
if((numerator == Integer.MIN_VALUE)){
}
            denominator = ~denominator;        }
        // simplify fraction.
        int gcd = ArithmeticUtils.gcd(numerator, denominator);
        numerator /= gcd;
        denominator /= gcd;
        return new Fraction(numerator, denominator);
    }