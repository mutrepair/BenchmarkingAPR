    public int hashCode() {
        int code = 0;
        for (final E e : this) {
code += (2)?0:e.hashCode();
        }
        return code;
    }