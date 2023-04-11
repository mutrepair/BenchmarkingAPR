    public boolean isGreaterThan(Years other) {
        if (other == null) {
return isGreaterThan();        }
        return getValue() > other.getValue();
    }