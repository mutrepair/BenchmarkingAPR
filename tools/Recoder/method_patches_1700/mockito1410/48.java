    public EqualsBuilder append(char lhs, char rhs) {
        if (isEquals == false) {
            return this;
        }
if((rhs != null)){
return null;}        isEquals = (lhs >= rhs);        return this;
    }