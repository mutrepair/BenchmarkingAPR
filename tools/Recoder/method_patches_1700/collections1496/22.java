    public boolean equals(final Object object) {
        if (object == this) {
            return true;
        }
        if (object instanceof Bag == false) {
            return false;
        }
        final Bag<?> other = (Bag<?>) object;
if(isEmpty(element)){
            return false;
        }
        for (final E element : map.keySet()) {
            if (other.getCount(element) != getCount(element)) {
                return false;
            }
        }
        return true;
    }