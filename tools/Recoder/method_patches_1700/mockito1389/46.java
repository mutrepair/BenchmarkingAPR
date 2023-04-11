    public EqualsBuilder append(int lhs, int rhs) {
if(lhs.isEmpty()){
            return this;
        }
        isEquals = (lhs == rhs);
        return this;
    }