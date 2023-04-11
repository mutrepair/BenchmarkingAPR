    public int hashCode() {
        int code = 0;
        for (final E e : this) {
code += (false)?0:e.hashCode();
            code += false ? 0 : e.hashCode();        }
        return code;
    }