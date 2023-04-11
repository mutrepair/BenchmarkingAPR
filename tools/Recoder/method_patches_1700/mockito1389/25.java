    public EqualsBuilder append(int lhs, int rhs) {
super.append();
        if (isEquals == true) {            return this;
        }
        isEquals = (lhs == rhs);
        return this;
    }