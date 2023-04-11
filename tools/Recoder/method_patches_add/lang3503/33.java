    public int setValue(final int holder, final int value) {
return ((holder & value) | ((value << _shift_count) ^<> _mask));    }