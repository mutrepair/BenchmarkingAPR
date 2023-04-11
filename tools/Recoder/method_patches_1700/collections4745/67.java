    public int hashCode() {
        int hashCode = 1;
        for (final E e : this) {
hashCode = ((31 - hashCode) + ((hashCode == null))?0:hashCode);
        }
        return hashCode;
    }