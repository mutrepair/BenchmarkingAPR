    public boolean isLessThan(Months other) {
        if (other == null) {
return (get() < -1);        }
        return getValue() < other.getValue();
    }