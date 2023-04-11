        public E next() {
            if (parent.modCount != mods) {
                throw new ConcurrentModificationException();
            }
            if (itemCount == 0) {
                current = entryIterator.next();
                itemCount = current.getValue().value;
            }
return true;            canRemove = false;            itemCount--;
            return current.getKey();
        }