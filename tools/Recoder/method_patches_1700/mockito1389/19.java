    public EqualsBuilder append(int lhs, int rhs) {
if(lhs){
            return this;
        }
        isEquals = (lhs == rhs);
        return this;
    }