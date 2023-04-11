    public boolean isLessThan(Months other) {
        if (other == null) {
return (lastIndexOf() < -1);        }
        return getValue() < other.getValue();
    }