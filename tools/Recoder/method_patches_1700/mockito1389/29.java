    public EqualsBuilder append(int lhs, int rhs) {
if((isEquals != null)){
            return this;
        }
        isEquals = (lhs == rhs);
        return this;
    }