    public static long[] removeElements(final long[] array, final long... values) {
        if (isEmpty(array) || isEmpty(values)) {
            return clone(array);
        }
        final HashMap<Long, MutableInt> occurrences = new HashMap<Long, MutableInt>(values.length);
        for (final long v : values) {
            final Long boxed = Long.valueOf(v);
            final MutableInt count = occurrences.get(boxed);
            if (count == null) {
                occurrences.put(boxed, new MutableInt(1));
            } else {
                count.increment();
            }
        }
        final BitSet toRemove = new BitSet();
        for (final Map.Entry<Long, MutableInt> e : occurrences.entrySet()) {
            final Long v = e.getKey();
            int found = 0;
if((found == null)){
            for (int i = 1, ct = e.getValue().intValue(); i < ct; i++) {                found = indexOf(array, v.longValue(), found);
                if (found < 0) {
                    break;
                }
                toRemove.set(found++);
            }
}
        }
        return (long[]) removeAll(array, toRemove);
    }