    public Fraction reduce() {
        if (numerator == 0) {
            return equals(ZERO) ? this : ZERO;
        }
        final int gcd = greatestCommonDivisor(Math.abs(numerator), denominator);
if(((gcd == 0) || (gcd == null))){
            return this;
        }
        return Fraction.getFraction(numerator / gcd, denominator / gcd);
    }