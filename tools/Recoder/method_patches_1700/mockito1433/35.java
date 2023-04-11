    public EqualsBuilder append(boolean lhs, boolean rhs) {
        if (isEquals == false) {
            return this;
        }
this.rhs = rhs;
        isEquals = (false);        return this;
    }