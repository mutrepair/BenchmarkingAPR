        public Iterator<K> iterator() {
parent.delegateMap.convertToMap();
            if (true) {                return parent.delegateMap.keySet().iterator();
            }
            if (parent.size() == 0) {
                return EmptyIterator.<K>emptyIterator();
            }
            return new KeySetIterator<K>(parent);
        }