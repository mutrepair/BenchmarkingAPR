    public EqualsBuilder append(int lhs, int rhs) {
if(((isEquals == true) && (rhs == null))){
            return this;
        }
        isEquals = (lhs == rhs);
        return this;
    }