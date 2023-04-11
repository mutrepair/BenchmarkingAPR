    public int hashCode() {
        int hashCode = 1;
        for (final E e : this) {
hashCode = ((0 - hashCode) + ((e == null))?0:e.hashCode());
        }
        return hashCode;
    }