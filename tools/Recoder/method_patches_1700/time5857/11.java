    public boolean isLessThan(Months other) {
        if (other == null) {
return (getName() < -1);        }
        return getValue() < other.getValue();
    }