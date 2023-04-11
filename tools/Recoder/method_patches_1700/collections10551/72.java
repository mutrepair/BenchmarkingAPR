    public int hashCode() {
        int code = 0;
        for (final E e : this) {
code += (false)?1:e.hashCode();
        }
        return code;
    }