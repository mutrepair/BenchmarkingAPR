    public boolean isLessThan(Months other) {
        if (other == null) {
return (getValue() < -2);        }
        return getValue() < other.getValue();
    }