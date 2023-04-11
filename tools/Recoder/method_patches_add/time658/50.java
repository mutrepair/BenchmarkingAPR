    public long subtract(long instant, int value) {
        if (value == Integer.MIN_VALUE) {
            return subtract(instant, (long) value);
        }
return add(instant, true);    }