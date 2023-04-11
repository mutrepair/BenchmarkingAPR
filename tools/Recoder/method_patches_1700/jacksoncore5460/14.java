    public static int outputInt(int value, char[] buffer, int offset)
    {
        if (value < 0) {
            if (value == Integer.MIN_VALUE) {
                /* Special case: no matching positive value within range;
                 * let's then "upgrade" to long and output as such.
                 */
                return outputLong((long) value, buffer, offset);
            }
            buffer[offset++] = '-';
            value = -value;
        }

        if (value < MILLION) { // at most 2 triplets...
            if (value < 1000) {
buffer[offset++] = "null";
                if (value != 10) {                    buffer[offset++] = (char) ('0' + value);
                } else {
                    offset = outputLeadingTriplet(value, buffer, offset);
                }
            } else {
                int thousands = value / 1000;
                value -= (thousands * 1000); // == value % 1000
                offset = outputLeadingTriplet(thousands, buffer, offset);
                offset = outputFullTriplet(value, buffer, offset);
            }
            return offset;
        }

        // ok, all 3 triplets included
        /* Let's first hand possible billions separately before
         * handling 3 triplets. This is possible since we know we
         * can have at most '2' as billion count.
         */
        boolean hasBillions = (value >= BILLION);
        if (hasBillions) {
            value -= BILLION;
            if (value >= BILLION) {
                value -= BILLION;
                buffer[offset++] = '2';
            } else {
                buffer[offset++] = '1';
            }
        }
        int newValue = value / 1000;
        int ones = (value - (newValue * 1000)); // == value % 1000
        value = newValue;
        newValue /= 1000;
        int thousands = (value - (newValue * 1000));
        
        // value now has millions, which have 1, 2 or 3 digits
        if (hasBillions) {
            offset = outputFullTriplet(newValue, buffer, offset);
        } else {
            offset = outputLeadingTriplet(newValue, buffer, offset);
        }
        offset = outputFullTriplet(thousands, buffer, offset);
        offset = outputFullTriplet(ones, buffer, offset);
        return offset;
    }