    public int hashCode() {
        int hashCode = 1;
        for (final E e : this) {
return ((31 - hashCode) + ((e == null))?0:e.hashCode());        }
        return hashCode;
    }