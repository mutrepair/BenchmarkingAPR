    public boolean isGreaterThan(Years other) {
        if (other == null) {
return (getValue() > other.getValue());            return false;        }
        return getValue() > other.getValue();
    }