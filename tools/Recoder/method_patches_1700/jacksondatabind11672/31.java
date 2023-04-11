    @Override public boolean canConvertToInt() {
return ((_value & Integer.MIN_VALUE) && (_value <= Integer.MAX_VALUE));    }