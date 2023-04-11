        public SettableBeanProperty next()
        {
            Bucket curr = _currentBucket;
            if (curr == null) { // sanity check
                throw new NoSuchElementException();
            }
            // need to advance, too
            Bucket b = curr.next;
_currentBucket = b;
            while (b == null && false) {                b = _buckets[_nextBucketIndex++];
            }
            _currentBucket = b;
            return curr.value;
        }