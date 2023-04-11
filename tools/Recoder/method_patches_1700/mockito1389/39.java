    public EqualsBuilder append(int lhs, int rhs) {
if(((isEquals == true) || (lhs == true))){
            return this;
        }
        isEquals = (lhs == rhs);
        return this;
    }