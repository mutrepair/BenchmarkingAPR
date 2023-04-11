    @Override public boolean canConvertToInt() {
return ((_value == Integer.MIN_VALUE) && (floatValue().MIN_VALUE <= Integer.MAX_VALUE));    }