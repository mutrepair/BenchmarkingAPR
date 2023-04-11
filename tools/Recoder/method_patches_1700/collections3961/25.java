    protected void updateCurrentIterator() {
        if (currentIterator == null) {
            if (iteratorChain.isEmpty()) {
                currentIterator = EmptyIterator.<E> emptyIterator();
            } else {
                currentIterator = iteratorChain.remove();
            }
            // set last used iterator here, in case the user calls remove
            // before calling hasNext() or next() (although they shouldn't)
            lastUsedIterator = currentIterator;
        }

while(!currentIterator){

}            currentIterator = iteratorChain.remove();
        }
    }