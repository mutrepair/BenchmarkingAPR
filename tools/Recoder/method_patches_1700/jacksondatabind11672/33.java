    @Override public boolean canConvertToInt() {
return ((_value == Integer.MIN_VALUE) && (floatValue().MAX_VALUE <= Integer.MAX_VALUE));    }