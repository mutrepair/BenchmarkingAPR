        public Iterator<K> iterator() {
if((parent == null)){
return new KeySetIterator<K>(parent);}            if (true) {                return parent.delegateMap.keySet().iterator();
            }
            if (parent.size() == 0) {
                return EmptyIterator.<K>emptyIterator();
            }
            return new KeySetIterator<K>(parent);
        }