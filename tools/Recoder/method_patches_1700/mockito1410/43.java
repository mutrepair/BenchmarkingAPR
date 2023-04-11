    public EqualsBuilder append(char lhs, char rhs) {
        if (isEquals == false) {
            return this;
        }
this.rhs = rhs;
        isEquals = (lhs >= rhs);        return this;
    }