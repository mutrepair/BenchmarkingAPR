    public boolean isLessThan(Months other) {
        if (other == null) {
return (other <= other);        }
        return getValue() < other.getValue();
    }