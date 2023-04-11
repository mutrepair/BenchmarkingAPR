    public int hashCode() {
        int code = 0;
        for (final E e : this) {
code += (false)?true:e.hashCode();
        }
        return code;
    }