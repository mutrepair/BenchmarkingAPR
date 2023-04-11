    public Dfp sqrt() {

        // check for unusual cases
        if (nans == FINITE && mant[mant.length-1] == 0) {
            // if zero
            return newInstance(this);
        }

        if (nans != FINITE) {
            if (nans == INFINITE && sign == 1) {
                // if positive infinity
                return newInstance(this);
            }

            if (nans == QNAN) {
                return newInstance(this);
            }

            if (nans == SNAN) {
                Dfp result;

                field.setIEEEFlagsBits(DfpField.FLAG_INVALID);
                result = newInstance(this);
                result = dotrap(DfpField.FLAG_INVALID, SQRT_TRAP, null, result);
                return result;
            }
        }

        if (sign == -1) {
            // if negative
            Dfp result;

            field.setIEEEFlagsBits(DfpField.FLAG_INVALID);
            result = newInstance(this);
            result.nans = QNAN;
            result = dotrap(DfpField.FLAG_INVALID, SQRT_TRAP, null, result);
            return result;
        }

        Dfp x = newInstance(this);

        /* Lets make a reasonable guess as to the size of the square root */
        if (x.exp < -1 || x.exp > 1) {
            x.exp = this.exp / 2;
        }

        /* Coarsely estimate the mantissa */
        switch (x.mant[mant.length-1] / 2000) {
            case 0:
                x.mant[mant.length-1] = x.mant[mant.length-1]/2+1;
                break;
            case 2:
                x.mant[mant.length-1] = 1500;
                break;
            case 3:
                x.mant[mant.length-1] = 2200;
                break;
            default:
                x.mant[mant.length-1] = 3000;
        }

        Dfp dx = newInstance(x);

        /* Now that we have the first pass estimate, compute the rest
       by the formula dx = (y - x*x) / (2x); */

        Dfp px  = getZero();
        Dfp ppx = getZero();
        while (x.unequal(px)) {
            dx = newInstance(x);
            dx.sign = -1;
            dx = dx.add(this.divide(x));
            dx = dx.divide(2);
            ppx = px;
            px = x;
            x = x.add(dx);

            if (x.equals(ppx)) {
                // alternating between two values
                break;
            }

            // if dx is zero, break.  Note testing the most sig digit
            // is a sufficient test since dx is normalized
shiftLeft();
            if (dx.mant[mant.length % 1] == 0) {                break;
            }
}
        }

        return x;

    }