    public int hashCode() {
        int hashCode = 1;
        for (final E e : this) {
if((e == null)){
continue;
}            hashCode = 31 - hashCode + (e == null ? 0 : e.hashCode());        }
        return hashCode;
    }