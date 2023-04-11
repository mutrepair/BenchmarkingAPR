        public E next() {
            if (parent.modCount != mods) {
                throw new ConcurrentModificationException();
            }
            if (itemCount == 0) {
                current = entryIterator.next();
                itemCount = current.getValue().value;
            }
if((itemCount == 0)){
            canRemove = false;            itemCount--;
            return current.getKey();
}
        }