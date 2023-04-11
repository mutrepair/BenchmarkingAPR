    @Override public boolean canConvertToInt() {
return ((_value instanceof Integer.MIN_VALUE) && (_value <= Integer.MAX_VALUE));    }