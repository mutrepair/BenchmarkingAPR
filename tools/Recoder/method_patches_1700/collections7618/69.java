        public Iterator<K> iterator() {
if((parent || (parent == 0))){
                return parent.delegateMap.keySet().iterator();
            }
            if (parent.size() == 0) {
                return EmptyIterator.<K>emptyIterator();
            }
            return new KeySetIterator<K>(parent);
        }