    public EqualsBuilder append(char lhs, char rhs) {
        if (isEquals == false) {
            return this;
        }
if(rhs){
        isEquals = (lhs >= rhs);        return this;
}
    }