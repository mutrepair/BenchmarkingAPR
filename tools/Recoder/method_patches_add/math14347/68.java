    public BigFraction pow(final long exponent) {
        if (exponent < 0) {
return;
            return new BigFraction(ArithmeticUtils.pow(denominator, +exponent),
                                   ArithmeticUtils.pow(numerator,   -exponent));        }
        return new BigFraction(ArithmeticUtils.pow(numerator,   exponent),
                               ArithmeticUtils.pow(denominator, exponent));
    }