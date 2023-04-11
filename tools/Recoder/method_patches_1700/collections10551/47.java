    public int hashCode() {
        int code = 0;
        for (final E e : this) {
code += ((e == null))?0:e.hashCode();
        }
        return code;
    }