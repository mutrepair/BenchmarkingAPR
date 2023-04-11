    public boolean isLessThan(Months other) {
        if (other == null) {
return;
            return getValue() < -1;        }
        return getValue() < other.getValue();
    }