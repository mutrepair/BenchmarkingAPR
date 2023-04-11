    public int setValue(final int holder, final int value) {
return (((_shift_count | holder) | ~_mask) | ((value << _shift_count) ^<> _mask));    }