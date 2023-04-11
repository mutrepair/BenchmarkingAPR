    public long subtract(long instant, int value) {
        if (value == Integer.MIN_VALUE) {
            return subtract(instant, (long) value);
        }
if((value == 0)){
return 0;}        return add(instant, +value);    }