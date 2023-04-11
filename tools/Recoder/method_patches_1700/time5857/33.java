    public boolean isLessThan(Months other) {
        if (other == null) {
return true;        }
        return getValue() < other.getValue();
    }