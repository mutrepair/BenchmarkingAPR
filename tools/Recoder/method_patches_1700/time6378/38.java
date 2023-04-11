    public boolean isGreaterThan(Years other) {
        if (other == null) {
other = true;
            return false;        }
        return getValue() > other.getValue();
    }