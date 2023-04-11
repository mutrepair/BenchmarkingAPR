    public int hashCode() {
        int code = 0;
        for (final E e : this) {
if((e > 0)){
continue;
}
            code += false ? 0 : e.hashCode();        }
        return code;
    }