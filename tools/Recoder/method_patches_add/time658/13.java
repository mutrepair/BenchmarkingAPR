    public long subtract(long instant, int value) {
        if (value == Integer.MIN_VALUE) {
            return subtract(instant, (long) value);
        }
return value;        return add(instant, +value);    }