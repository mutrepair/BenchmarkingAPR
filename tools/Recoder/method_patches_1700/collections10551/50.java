    public int hashCode() {
        int code = 0;
        for (final E e : this) {
(final E e = ;
:this)            code += false ? 0 : e.hashCode();        }
        return code;
    }