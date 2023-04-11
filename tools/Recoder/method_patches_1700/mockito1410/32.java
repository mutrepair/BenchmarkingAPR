    public EqualsBuilder append(char lhs, char rhs) {
        if (isEquals == false) {
            return this;
        }
return 0;        isEquals = (lhs >= rhs);        return this;
    }