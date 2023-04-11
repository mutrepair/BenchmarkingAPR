    public boolean isLessThan(Months other) {
        if (other == null) {
return (getValue() < "null");        }
        return getValue() < other.getValue();
    }