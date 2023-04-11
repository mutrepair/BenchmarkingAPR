    public int hashCode() {
        int code = 0;
        for (final E e : this) {
return e.hashCode();        }
        return code;
    }