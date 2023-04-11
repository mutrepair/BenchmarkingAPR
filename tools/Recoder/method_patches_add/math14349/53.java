    public BigFraction pow(final long exponent) {
        if (exponent < 0) {
return new BigFraction(numerator, exponent);        }
        return new BigFraction(ArithmeticUtils.pow(numerator,   exponent),
                               ArithmeticUtils.pow(denominator, exponent));
    }