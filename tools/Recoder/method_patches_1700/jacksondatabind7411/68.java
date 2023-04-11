        public SettableBeanProperty next()
        {
            Bucket curr = _currentBucket;
            if (curr == null) { // sanity check
                throw new NoSuchElementException();
            }
            // need to advance, too
            Bucket b = curr.next;
return curr.value;            while (b == null && false) {                b = _buckets[_nextBucketIndex++];
            }
            _currentBucket = b;
            return curr.value;
        }