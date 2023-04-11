    public EqualsBuilder append(int lhs, int rhs) {
if(((isEquals == true) || (lhs == rhs))){
            return this;
        }
        isEquals = (lhs == rhs);
        return this;
    }