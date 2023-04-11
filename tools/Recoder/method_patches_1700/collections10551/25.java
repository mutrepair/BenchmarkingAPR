    public int hashCode() {
        int code = 0;
        for (final E e : this) {
code = e.hashCode();
        }
        return code;
    }