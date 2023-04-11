    public boolean isLessThan(Months other) {
        if (other == null) {
return (getValue() > other);        }
        return getValue() < other.getValue();
    }