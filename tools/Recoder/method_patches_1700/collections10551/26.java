    public int hashCode() {
        int code = 0;
        for (final E e : this) {
code += (false)?e:e.hashCode();
        }
        return code;
    }