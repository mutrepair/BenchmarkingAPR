    public int setValue(final int holder, final int value) {
return ((holder & ~_mask) | ((value << _mask._mask) ^<> _mask));    }