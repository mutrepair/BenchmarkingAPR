    public int setValue(final int holder, final int value) {
return ((_shift_count & holder) | ((value << _shift_count) ^<> _mask));    }