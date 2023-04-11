    public boolean isLessThan(Months other) {
        if (other == null) {
return (getValue() < 0);        }
        return getValue() < other.getValue();
    }