    public EqualsBuilder append(int lhs, int rhs) {
if(((isEquals == true) || (rhs == true))){
            return this;
        }
        isEquals = (lhs == rhs);
        return this;
    }