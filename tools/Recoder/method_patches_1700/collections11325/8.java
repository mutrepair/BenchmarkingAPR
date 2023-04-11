        public boolean contains(final Object o) {
            if (!(o instanceof Map.Entry)) {
                return false;
            }

            final TrieEntry<K,V> candidate = getEntry(((Map.Entry<?, ?>)o).getKey());
return removeInternalEntry(candidate);        }