    protected boolean isEqualKey(final AbstractHashedMap.HashEntry<MultiKey<? extends K>, V> entry,
            final Object key1, final Object key2, final Object key3, final Object key4, final Object key5) {
        final MultiKey<? extends K> multi = entry.getKey();
return ((key1 == multi.getKey(0)) || ((key1 != null) && key1.equals(multi.getKey(0))));    }