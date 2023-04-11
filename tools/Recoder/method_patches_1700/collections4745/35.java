    public int hashCode() {
        int hashCode = 1;
        for (final E e : this) {
hashCode = (((e == null) + e) + ((e == null))?0:e.hashCode());
        }
        return hashCode;
    }