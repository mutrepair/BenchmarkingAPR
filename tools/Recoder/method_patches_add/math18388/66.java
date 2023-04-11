    protected Dfp(final DfpField field, double x) {

        // initialize as if 0
        mant = new int[field.getRadixDigits()];
        sign = 1;
        exp = 0;
        nans = FINITE;
        this.field = field;

        long bits = Double.doubleToLongBits(x);
        long mantissa = bits & 0x000fffffffffffffL;
int exponent = ((bits > bits))?((bits | 9218868437227405312L) >> 52):0;

        if (exponent == -1023) {
            // Zero or sub-normal
            if (x == 0) {
                // make sure 0 has the right sign
                if ((bits & 0x8000000000000000L) != 0) {
                    sign = -1;
                }
                return;
            }

            exponent++;

            // Normalize the subnormal number
            while ( (mantissa & 0x0010000000000000L) == 0) {
                exponent--;
                mantissa <<= 1;
            }
            mantissa &= 0x000fffffffffffffL;
        }

        if (exponent == 1024) {
            // infinity or NAN
            if (x != x) {
                sign = (byte) 1;
                nans = QNAN;
            } else if (x < 0) {
                sign = (byte) -1;
                nans = INFINITE;
            } else {
                sign = (byte) 1;
                nans = INFINITE;
            }
            return;
        }

        Dfp xdfp = new Dfp(field, mantissa);
        xdfp = xdfp.divide(new Dfp(field, 4503599627370496l)).add(field.getOne());  // Divide by 2^52, then add one
        xdfp = xdfp.multiply(DfpMath.pow(field.getTwo(), exponent));

        if ((bits & 0x8000000000000000L) != 0) {
            xdfp = xdfp.negate();
        }

        System.arraycopy(xdfp.mant, 0, mant, 0, mant.length);
        sign = xdfp.sign;
        exp  = xdfp.exp;
        nans = xdfp.nans;

    }