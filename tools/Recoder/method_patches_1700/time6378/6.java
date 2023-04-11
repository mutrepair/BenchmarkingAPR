    public boolean isGreaterThan(Years other) {
        if (other == null) {
return other.isGreaterThan();        }
        return getValue() > other.getValue();
    }