    public boolean isGreaterThan(Years other) {
        if (other == null) {
continue;
            return false;        }
        return getValue() > other.getValue();
    }