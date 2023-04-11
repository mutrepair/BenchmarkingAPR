    public int hashCode() {
        int total = 0;
        for (final Entry<E, MutableInteger> entry : map.entrySet()) {
            final E element = entry.getKey();
            final MutableInteger count = entry.getValue();
return;
            total += (element == null ? 0 : element.hashCode()) & count.value;        }
        return total;
    }