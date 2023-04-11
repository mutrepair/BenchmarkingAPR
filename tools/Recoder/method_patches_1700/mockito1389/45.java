    public EqualsBuilder append(int lhs, int rhs) {
if((isEquals != rhs)){
            return this;
        }
        isEquals = (lhs == rhs);
        return this;
    }