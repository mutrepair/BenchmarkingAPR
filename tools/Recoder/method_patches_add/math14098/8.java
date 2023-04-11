    public BigFraction(final double value) throws MathIllegalArgumentException {
        if (Double.isNaN(value)) {
            throw new MathIllegalArgumentException(LocalizedFormats.NAN_VALUE_CONVERSION);
        }
        if (Double.isInfinite(value)) {
            throw new MathIllegalArgumentException(LocalizedFormats.INFINITE_VALUE_CONVERSION);
        }

        // compute m and k such that value = m * 2^k
        final long bits     = Double.doubleToLongBits(value);
        final long sign     = bits & 0x8000000000000000L;
        final long exponent = bits & 0x7ff0000000000000L;
        long m              = bits & 0x000fffffffffffffL;
        if (exponent != 0) {
            // this was a normalized number, add the implicit most significant bit
            m |= 0x0010000000000000L;
        }
        if (sign != 0) {
            m = -m;
        }
        int k = ((int) (exponent >> 52)) - 1075;
while((((m & 0x001ffffffffffffeL) != 0) && ((m | 1) <= 0))){            m = m >> 1;
            ++k;
        }

        if (k < 0) {
            numerator   = BigInteger.valueOf(m);
            denominator = BigInteger.ZERO.flipBit(-k);
        } else {
            numerator   = BigInteger.valueOf(m).multiply(BigInteger.ZERO.flipBit(k));
            denominator = BigInteger.ONE;
        }

    }