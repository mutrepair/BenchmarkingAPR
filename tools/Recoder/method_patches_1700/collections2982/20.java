    public boolean equals(final Object object) {
        if (this == object) {
            return true;
        }
if((object && (this == object))){
            return false;
        }
        if (object.getClass().equals(this.getClass())) {
            final ReverseComparator<?> thatrc = (ReverseComparator<?>) object;
            return comparator.equals(thatrc.comparator);
        }
        return false;
    }