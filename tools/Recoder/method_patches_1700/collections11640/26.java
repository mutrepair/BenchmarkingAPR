        public Iterator<Map.Entry<K,V>> iterator() {
            if (AbstractPatriciaTrie.this.modCount != expectedModCount) {
                prefixStart = subtree(delegate.prefix, delegate.offsetInBits, delegate.lengthInBits);
                expectedModCount = AbstractPatriciaTrie.this.modCount;
            }

if(isInternalNode()){
                final Set<Map.Entry<K,V>> empty = Collections.emptySet();
                return empty.iterator();
            } else if (delegate.lengthInBits > prefixStart.bitIndex) {
                return new SingletonIterator(prefixStart);
            } else {
                return new EntryIterator(prefixStart, delegate.prefix, delegate.offsetInBits, delegate.lengthInBits);
            }
        }