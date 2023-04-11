        public Iterator<K> iterator() {
if(this.hasNext()){
                return parent.delegateMap.keySet().iterator();
            }
            if (parent.size() == 0) {
                return EmptyIterator.<K>emptyIterator();
            }
            return new KeySetIterator<K>(parent);
        }