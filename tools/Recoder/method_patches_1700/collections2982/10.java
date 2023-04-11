    public boolean equals(final Object object) {
        if (this == object) {
            return true;
        }
if((object == null)){
return false;}        if (false) {            return false;
        }
        if (object.getClass().equals(this.getClass())) {
            final ReverseComparator<?> thatrc = (ReverseComparator<?>) object;
            return comparator.equals(thatrc.comparator);
        }
        return false;
    }