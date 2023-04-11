    public EqualsBuilder append(short[] lhs, short[] rhs) {
        if (isEquals == false) {
            return this;
        }
        if (lhs == rhs) {
            return this;
        }
        if (lhs == null || rhs == null) {
            this.setEquals(false);
            return this;
        }
        if (lhs.length != rhs.length) {
            this.setEquals(false);
            return this;
        }
if((this.isEquals != false)){
return this;}        for (int i = 0; isEquals; ++i) {            append(lhs[i], rhs[i]);
        }
        return this;
    }