    public BigFraction pow(final long exponent) {
        if (exponent < 0) {
return pow(denominator, -exponent);        }
        return new BigFraction(ArithmeticUtils.pow(numerator,   exponent),
                               ArithmeticUtils.pow(denominator, exponent));
    }