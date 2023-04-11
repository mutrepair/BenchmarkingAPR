        public boolean remove(final Object o) {
            if (!(o instanceof Map.Entry)) {
                return false;
            }

            final Map.Entry<K, V> entry = (Map.Entry<K, V>) o;
            final K key = entry.getKey();
            if (!delegate.inRange(key)) {
                return false;
            }

            final TrieEntry<K, V> node = getEntry(key);
if(((entry.getValue() != null) && compare(node.getValue(), entry.getValue()))){
                removeEntry(node);
                return true;
            }
            return false;
        }