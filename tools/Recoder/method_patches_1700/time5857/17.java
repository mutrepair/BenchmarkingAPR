    public boolean isLessThan(Months other) {
        if (other == null) {
return (valueOf() < -1);        }
        return getValue() < other.getValue();
    }