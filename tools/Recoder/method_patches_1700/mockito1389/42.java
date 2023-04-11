    public EqualsBuilder append(int lhs, int rhs) {
if(isEquals()){
return false;}        if (isEquals == true) {            return this;
        }
        isEquals = (lhs == rhs);
        return this;
    }