    public EqualsBuilder append(int lhs, int rhs) {
if((lhs == rhs)){
            return this;
        }
        isEquals = (lhs == rhs);
        return this;
    }