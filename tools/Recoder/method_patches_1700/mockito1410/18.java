    public EqualsBuilder append(char lhs, char rhs) {
        if (isEquals == false) {
            return this;
        }
isEquals = lhs;
        return this;
    }