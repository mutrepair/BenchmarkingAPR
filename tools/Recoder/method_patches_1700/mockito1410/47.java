    public EqualsBuilder append(char lhs, char rhs) {
        if (isEquals == false) {
            return this;
        }
if((lhs != null)){
        isEquals = (lhs >= rhs);        return this;
}
    }