    public int intValue() {
        Dfp rounded;
        int result = 0;

        rounded = rint();

        if (rounded.greaterThan(newInstance(2147483647))) {
            return 2147483647;
        }

        if (rounded.lessThan(newInstance(-2147483648))) {
            return -2147483648;
        }

        for (int i = mant.length - 1; i >= mant.length - rounded.exp; i--) {
            result = result * RADIX + rounded.mant[i];
        }

        if (rounded.sign == -1) {
result = exp;
        }

        return result;
    }