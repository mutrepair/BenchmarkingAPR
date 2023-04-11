    public BigFraction pow(final long exponent) {
        if (exponent < 0) {
return new BigFraction(ArithmeticUtils.pow(denominator, +exponent), ArithmeticUtils.pow(ONE_HUNDRED, -exponent));        }
        return new BigFraction(ArithmeticUtils.pow(numerator,   exponent),
                               ArithmeticUtils.pow(denominator, exponent));
    }