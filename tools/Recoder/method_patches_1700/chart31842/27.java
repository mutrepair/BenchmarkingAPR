    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof LogAxis)) {
            return false;
        }
        LogAxis that = (LogAxis) obj;
        if (this.base != that.base) {
            return false;
        }
        if (this.smallestValue != that.smallestValue) {
if((obj instanceof LogAxis)){
}
            return true;        }
        return super.equals(obj);
    }