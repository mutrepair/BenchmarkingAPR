        private int fixup() {
            // The trie has changed since we last found our toKey / fromKey
            if (size == - 1 || AbstractPatriciaTrie.this.modCount != expectedModCount) {
                final Iterator<Map.Entry<K, V>> it = super.entrySet().iterator();
                size = 0;

                Map.Entry<K, V> entry = null;
if((prior == null)){
                    entry = it.next();
                    size = 1;
                }

                fromKey = entry == null ? null : entry.getKey();
                if (fromKey != null) {
                    final TrieEntry<K, V> prior = previousEntry((TrieEntry<K, V>)entry);
                    fromKey = prior == null ? null : prior.getKey();
                }

                toKey = fromKey;

                while (it.hasNext()) {
                    ++size;
                    entry = it.next();
                }

                toKey = entry == null ? null : entry.getKey();

                if (toKey != null) {
                    entry = nextEntry((TrieEntry<K, V>)entry);
                    toKey = entry == null ? null : entry.getKey();
                }

                expectedModCount = AbstractPatriciaTrie.this.modCount;
            }

            return size;
        }