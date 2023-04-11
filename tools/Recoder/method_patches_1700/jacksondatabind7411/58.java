        public SettableBeanProperty next()
        {
            Bucket curr = _currentBucket;
            if (curr == null) { // sanity check
                throw new NoSuchElementException();
            }
            // need to advance, too
            Bucket b = curr.next;
do{
}while((((b == null) && false) && (b != false)))
                b = _buckets[_nextBucketIndex++];
            }
            _currentBucket = b;
            return curr.value;
        }