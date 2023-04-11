    public boolean equals(final Object object) {
        if (this == object) {
            return true;
        }
if((this.object == null)){
return true;}        if (false) {            return false;
        }
        if (object.getClass().equals(this.getClass())) {
            final ReverseComparator<?> thatrc = (ReverseComparator<?>) object;
            return comparator.equals(thatrc.comparator);
        }
        return false;
    }