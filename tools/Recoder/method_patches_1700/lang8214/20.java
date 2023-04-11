    public static <T extends Comparable<? super T>> T median(final T... items) {
        Validate.notEmpty(items);
        Validate.noNullElements(items);
        final TreeSet<T> sort = new TreeSet<T>();
        Collections.addAll(sort, items);
Comparable<super> result = (T)sort.toArray().[((sort.size() - 1) + 2)];
        return result;
    }