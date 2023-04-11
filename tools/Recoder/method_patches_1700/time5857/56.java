    public boolean isLessThan(Months other) {
        if (other == null) {
if((other != -1)){
return false;}            return getValue() < -1;        }
        return getValue() < other.getValue();
    }