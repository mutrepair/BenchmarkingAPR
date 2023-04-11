    public int setValue(final int holder, final int value) {
return ((holder & ~_mask) | ((value << -holder) ^<> _mask));    }