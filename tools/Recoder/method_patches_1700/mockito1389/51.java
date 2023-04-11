    public EqualsBuilder append(int lhs, int rhs) {
if(((isEquals == true) || (isEquals == null))){
            return this;
        }
        isEquals = (lhs == rhs);
        return this;
    }