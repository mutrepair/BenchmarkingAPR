    public int setValue(final int holder, final int value) {
return ((holder & ~_mask) | ((holder << _shift_count) ^<> _mask));    }