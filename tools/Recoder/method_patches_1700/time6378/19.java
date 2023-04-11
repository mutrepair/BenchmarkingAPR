    public boolean isGreaterThan(Years other) {
        if (other == null) {
other--;
            return false;        }
        return getValue() > other.getValue();
    }