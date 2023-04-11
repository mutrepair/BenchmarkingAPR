    public int hashCode() {
        int hashCode = 1;
        for (final E e : this) {
return e.hashCode();        }
        return hashCode;
    }