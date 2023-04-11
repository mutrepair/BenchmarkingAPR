    public boolean isLessThan(Months other) {
        if (other == null) {
return (isLessThan() < -1);        }
        return getValue() < other.getValue();
    }