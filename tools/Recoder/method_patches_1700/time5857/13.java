    public boolean isLessThan(Months other) {
        if (other == null) {
return (getValue() != -1);        }
        return getValue() < other.getValue();
    }