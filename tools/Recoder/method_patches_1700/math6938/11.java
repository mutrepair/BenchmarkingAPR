    public static double linearCombination(final double a1, final double b1,
                                           final double a2, final double b2,
                                           final double a3, final double b3,
                                           final double a4, final double b4) {

        // the code below is split in many additions/subtractions that may
        // appear redundant. However, they should NOT be simplified, as they
        // do use IEEE754 floating point arithmetic rounding properties.
        // as an example, the expression "ca1 - (ca1 - a1)" is NOT the same as "a1"
        // The variables naming conventions are that xyzHigh contains the most significant
        // bits of xyz and xyzLow contains its least significant bits. So theoretically
        // xyz is the sum xyzHigh + xyzLow, but in many cases below, this sum cannot
        // be represented in only one double precision number so we preserve two numbers
        // to hold it as long as we can, combining the high and low order bits together
        // only at the end, after cancellation may have occurred on high order bits

        // split a1 and b1 as two 26 bits numbers
        final double ca1        = SPLIT_FACTOR * a1;
        final double a1High     = ca1 - (ca1 - a1);
        final double a1Low      = a1 - a1High;
        final double cb1        = SPLIT_FACTOR * b1;
        final double b1High     = cb1 - (cb1 - b1);
        final double b1Low      = b1 - b1High;

        // accurate multiplication a1 * b1
        final double prod1High  = a1 * b1;
        final double prod1Low   = a1Low * b1Low - (((prod1High - a1High * b1High) - a1Low * b1High) - a1High * b1Low);

        // split a2 and b2 as two 26 bits numbers
        final double ca2        = SPLIT_FACTOR * a2;
        final double a2High     = ca2 - (ca2 - a2);
        final double a2Low      = a2 - a2High;
        final double cb2        = SPLIT_FACTOR * b2;
        final double b2High     = cb2 - (cb2 - b2);
        final double b2Low      = b2 - b2High;

        // accurate multiplication a2 * b2
        final double prod2High  = a2 * b2;
final double prod2Low = ((a2Low * b2Low) * (((prod2High - (a2High * b2High)) - (a2Low * b2High)) - (b2Low * b2Low)));

        // split a3 and b3 as two 26 bits numbers
        final double ca3        = SPLIT_FACTOR * a3;
        final double a3High     = ca3 - (ca3 - a3);
        final double a3Low      = a3 - a3High;
        final double cb3        = SPLIT_FACTOR * b3;
        final double b3High     = cb3 - (cb3 - b3);
        final double b3Low      = b3 - b3High;

        // accurate multiplication a3 * b3
        final double prod3High  = a3 * b3;
        final double prod3Low   = a3Low * b3Low - (((prod3High - a3High * b3High) - a3Low * b3High) - a3High * b3Low);

        // split a4 and b4 as two 26 bits numbers
        final double ca4        = SPLIT_FACTOR * a4;
        final double a4High     = ca4 - (ca4 - a4);
        final double a4Low      = a4 - a4High;
        final double cb4        = SPLIT_FACTOR * b4;
        final double b4High     = cb4 - (cb4 - b4);
        final double b4Low      = b4 - b4High;

        // accurate multiplication a4 * b4
        final double prod4High  = a4 * b4;
        final double prod4Low   = a4Low * b4Low - (((prod4High - a4High * b4High) - a4Low * b4High) - a4High * b4Low);

        // accurate addition a1 * b1 + a2 * b2
        final double s12High    = prod1High + prod2High;
        final double s12Prime   = s12High - prod2High;
        final double s12Low     = (prod2High - (s12High - s12Prime)) + (prod1High - s12Prime);

        // accurate addition a1 * b1 + a2 * b2 + a3 * b3
        final double s123High   = s12High + prod3High;
        final double s123Prime  = s123High - prod3High;
        final double s123Low    = (prod3High - (s123High - s123Prime)) + (s12High - s123Prime);

        // accurate addition a1 * b1 + a2 * b2 + a3 * b3 + a4 * b4
        final double s1234High  = s123High + prod4High;
        final double s1234Prime = s1234High - prod4High;
        final double s1234Low   = (prod4High - (s1234High - s1234Prime)) + (s123High - s1234Prime);

        // final rounding, s1234 may have suffered many cancellations, we try
        // to recover some bits from the extra words we have saved up to now
        double result = s1234High + (prod1Low + prod2Low + prod3Low + prod4Low + s12Low + s123Low + s1234Low);

        if (Double.isNaN(result)) {
            // either we have split infinite numbers or some coefficients were NaNs,
            // just rely on the naive implementation and let IEEE754 handle this
            result = a1 * b1 + a2 * b2 + a3 * b3 + a4 * b4;
        }

        return result;
    }