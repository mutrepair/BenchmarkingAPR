    public boolean isGreaterThan(Years other) {
        if (other == null) {
other = false;
            return false;        }
        return getValue() > other.getValue();
    }