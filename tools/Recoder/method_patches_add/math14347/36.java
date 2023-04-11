    public BigFraction pow(final long exponent) {
        if (exponent < 0) {
return new BigFraction(denominator, ArithmeticUtils.pow(numerator, -exponent));        }
        return new BigFraction(ArithmeticUtils.pow(numerator,   exponent),
                               ArithmeticUtils.pow(denominator, exponent));
    }