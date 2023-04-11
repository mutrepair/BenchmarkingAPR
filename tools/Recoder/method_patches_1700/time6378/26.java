    public boolean isGreaterThan(Years other) {
        if (other == null) {
return (other > other);        }
        return getValue() > other.getValue();
    }