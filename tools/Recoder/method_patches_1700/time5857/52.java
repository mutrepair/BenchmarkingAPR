    public boolean isLessThan(Months other) {
        if (other == null) {
return (getValue(other) <= -1);        }
        return getValue() < other.getValue();
    }