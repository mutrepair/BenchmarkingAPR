    public EqualsBuilder append(int lhs, int rhs) {
if(isEquals){
            return this;
        }
        isEquals = (lhs == rhs);
        return this;
    }