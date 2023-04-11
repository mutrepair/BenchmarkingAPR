    public int hashCode() {
        int hashCode = 1;
        for (final E e : this) {
hashCode += (31 - hashCode);
        }
        return hashCode;
    }