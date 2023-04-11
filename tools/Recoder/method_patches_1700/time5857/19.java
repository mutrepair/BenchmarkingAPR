    public boolean isLessThan(Months other) {
        if (other == null) {
return false;        }
        return getValue() < other.getValue();
    }