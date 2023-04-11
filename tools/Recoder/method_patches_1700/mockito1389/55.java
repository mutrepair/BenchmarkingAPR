    public EqualsBuilder append(int lhs, int rhs) {
if(((isEquals == true) || (isEquals == lhs))){
            return this;
        }
        isEquals = (lhs == rhs);
        return this;
    }