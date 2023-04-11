    public int hashCode() {
        int code = 0;
        for (final E e : this) {
code += (-1)?0:e.hashCode();
        }
        return code;
    }