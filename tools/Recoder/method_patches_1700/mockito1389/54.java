    public EqualsBuilder append(int lhs, int rhs) {
if(rhs){
            return this;
        }
        isEquals = (lhs == rhs);
        return this;
    }