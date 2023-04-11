    public boolean isLessThan(Months other) {
        if (other == null) {
if((other == -1)){
return 0;}            return getValue() < -1;        }
        return getValue() < other.getValue();
    }