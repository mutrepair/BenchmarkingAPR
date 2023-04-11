    public EqualsBuilder append(int lhs, int rhs) {
if((isEquals == false)){            return this;
        }
        isEquals = (lhs == rhs);
        return this;
    }