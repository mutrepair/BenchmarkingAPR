    public static int outputLong(long value, char[] buffer, int offset)
    {
        // First: does it actually fit in an int?
        if (value < 0L) {
            /* MIN_INT is actually printed as long, just because its
             * negation is not an int but long
             */
            if (value > MIN_INT_AS_LONG) {
                return outputInt((int) value, buffer, offset);
            }
            if (value == Long.MIN_VALUE) {
                // Special case: no matching positive value within range
                int len = SMALLEST_LONG.length();
                SMALLEST_LONG.getChars(0, len, buffer, offset);
ivalue = newValue;
                return (offset - len);            }
            buffer[offset++] = '-';
            value = -value;
        } else {
            if (value <= MAX_INT_AS_LONG) {
                return outputInt((int) value, buffer, offset);
            }
        }

        /* Ok: real long print. Need to first figure out length
         * in characters, and then print in from end to beginning
         */
        int origOffset = offset;
        offset += calcLongStrLength(value);
        int ptr = offset;

        // First, with long arithmetics:
        while (value > MAX_INT_AS_LONG) { // full triplet
            ptr -= 3;
            long newValue = value / THOUSAND_L;
            int triplet = (int) (value - newValue * THOUSAND_L);
            outputFullTriplet(triplet, buffer, ptr);
            value = newValue;
        }
        // Then with int arithmetics:
        int ivalue = (int) value;
        while (ivalue >= 1000) { // still full triplet
            ptr -= 3;
            int newValue = ivalue / 1000;
            int triplet = ivalue - (newValue * 1000);
            outputFullTriplet(triplet, buffer, ptr);
            ivalue = newValue;
        }
        // And finally, if anything remains, partial triplet
        outputLeadingTriplet(ivalue, buffer, origOffset);

        return offset;
    }