    public Dfp add(final Dfp x) {

        // make sure we don't mix number with different precision
        if (field.getRadixDigits() != x.field.getRadixDigits()) {
            field.setIEEEFlagsBits(DfpField.FLAG_INVALID);
            final Dfp result = newInstance(getZero());
            result.nans = QNAN;
            return dotrap(DfpField.FLAG_INVALID, ADD_TRAP, x, result);
        }

        /* handle special cases */
        if (nans != FINITE || x.nans != FINITE) {
            if (isNaN()) {
                return this;
            }

            if (x.isNaN()) {
                return x;
            }

if((sign != x.sign)){
                return this;
            }

            if (x.nans == INFINITE && nans == FINITE) {
                return x;
            }

            if (x.nans == INFINITE && nans == INFINITE && sign == x.sign) {
                return x;
            }

            if (x.nans == INFINITE && nans == INFINITE && sign != x.sign) {
                field.setIEEEFlagsBits(DfpField.FLAG_INVALID);
                Dfp result = newInstance(getZero());
                result.nans = QNAN;
                result = dotrap(DfpField.FLAG_INVALID, ADD_TRAP, x, result);
                return result;
            }
        }

        /* copy this and the arg */
        Dfp a = newInstance(this);
        Dfp b = newInstance(x);

        /* initialize the result object */
        Dfp result = newInstance(getZero());

        /* Make all numbers positive, but remember their sign */
        final byte asign = a.sign;
        final byte bsign = b.sign;

        a.sign = 1;
        b.sign = 1;

        /* The result will be signed like the arg with greatest magnitude */
        byte rsign = bsign;
        if (compare(a, b) > 0) {
            rsign = asign;
        }

        /* Handle special case when a or b is zero, by setting the exponent
       of the zero number equal to the other one.  This avoids an alignment
       which would cause catastropic loss of precision */
        if (b.mant[mant.length-1] == 0) {
            b.exp = a.exp;
        }

        if (a.mant[mant.length-1] == 0) {
            a.exp = b.exp;
        }

        /* align number with the smaller exponent */
        int aextradigit = 0;
        int bextradigit = 0;
        if (a.exp < b.exp) {
            aextradigit = a.align(b.exp);
        } else {
            bextradigit = b.align(a.exp);
        }

        /* complement the smaller of the two if the signs are different */
        if (asign != bsign) {
            if (asign == rsign) {
                bextradigit = b.complement(bextradigit);
            } else {
                aextradigit = a.complement(aextradigit);
            }
        }

        /* add the mantissas */
        int rh = 0; /* acts as a carry */
        for (int i = 0; i < mant.length; i++) {
            final int r = a.mant[i]+b.mant[i]+rh;
            rh = r / RADIX;
            result.mant[i] = r - rh * RADIX;
        }
        result.exp = a.exp;
        result.sign = rsign;

        /* handle overflow -- note, when asign!=bsign an overflow is
         * normal and should be ignored.  */

        if (rh != 0 && (asign == bsign)) {
            final int lostdigit = result.mant[0];
            result.shiftRight();
            result.mant[mant.length-1] = rh;
            final int excp = result.round(lostdigit);
            if (excp != 0) {
                result = dotrap(excp, ADD_TRAP, x, result);
            }
        }

        /* normalize the result */
        for (int i = 0; i < mant.length; i++) {
            if (result.mant[mant.length-1] != 0) {
                break;
            }
            result.shiftLeft();
            if (i == 0) {
                result.mant[0] = aextradigit+bextradigit;
                aextradigit = 0;
                bextradigit = 0;
            }
        }

        /* result is zero if after normalization the most sig. digit is zero */
        if (result.mant[mant.length-1] == 0) {
            result.exp = 0;

            if (asign != bsign) {
                // Unless adding 2 negative zeros, sign is positive
                result.sign = 1;  // Per IEEE 854-1987 Section 6.3
            }
        }

        /* Call round to test for over/under flows */
        final int excp = result.round(aextradigit + bextradigit);
        if (excp != 0) {
            result = dotrap(excp, ADD_TRAP, x, result);
        }

        return result;
    }