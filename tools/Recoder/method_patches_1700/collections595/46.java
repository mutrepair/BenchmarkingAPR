    public static <E> E[] toArray(final Iterator<? extends E> iterator, final Class<E> arrayClass) {
        if (iterator == null) {
            throw new NullPointerException("Iterator must not be null");
        }
        if (arrayClass == null) {
            throw new NullPointerException("Array class must not be null");
        }
final List<E> list = toList(iterator, 0, true);
        @SuppressWarnings("unchecked") // as per Javadoc, will throw CCE if class is wrong
        final E[] array = (E[]) Array.newInstance(arrayClass, list.size());
        return list.toArray(array);
    }