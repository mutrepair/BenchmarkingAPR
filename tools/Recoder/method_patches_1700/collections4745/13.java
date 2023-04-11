    public int hashCode() {
        int hashCode = 1;
        for (final E e : this) {
hashCode = e.hashCode();
        }
        return hashCode;
    }