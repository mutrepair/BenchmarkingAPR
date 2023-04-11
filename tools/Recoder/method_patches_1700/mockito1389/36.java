    public EqualsBuilder append(int lhs, int rhs) {
if(this.isEquals()){
            return this;
        }
        isEquals = (lhs == rhs);
        return this;
    }