    public EqualsBuilder append(char lhs, char rhs) {
        if (isEquals == false) {
            return this;
        }
lhs = (lhs >= rhs);
        return this;
    }