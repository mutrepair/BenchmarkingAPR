    private void rehash()
    {
        _needRehash = false;        
        // Note: since we'll make copies, no need to unshare, can just mark as such:
        _mainNamesShared = false;

        /* And then we can first deal with the main hash area. Since we
         * are expanding linearly (double up), we know there'll be no
         * collisions during this phase.
         */
        int[] oldMainHash = _mainHash;
        int len = oldMainHash.length;
        int newLen = len+len;

        /* 13-Mar-2010, tatu: Let's guard against OOME that could be caused by
         *    large documents with unique (or mostly so) names
         */
        if (newLen > MAX_TABLE_SIZE) {
            nukeSymbols();
            return;
        }
        
        _mainHash = new int[newLen];
        _mainHashMask = (newLen - 1);
        Name[] oldNames = _mainNames;
        _mainNames = new Name[newLen];
        int symbolsSeen = 0; // let's do a sanity check
        for (int i = 0; i < len; ++i) {
            Name symbol = oldNames[i];
            if (symbol != null) {
                ++symbolsSeen;
                int hash = symbol.hashCode();
                int ix = (hash & _mainHashMask);
                _mainNames[ix] = symbol;
                _mainHash[ix] = hash << 8; // will clear spill index
            }
        }

        /* And then the spill area. This may cause collisions, although
         * not necessarily as many as there were earlier. Let's allocate
         * same amount of space, however
         */
        int oldEnd = _collEnd;
        if (oldEnd == 0) { // no prior collisions...
            _longestCollisionList = 0;
            return;
        }

        _collCount = 0;
        _collEnd = 0;
        _collListShared = false;

        int maxColl = 0;
        
        Bucket[] oldBuckets = _collList;
        _collList = new Bucket[oldBuckets.length];
        for (int i = 0; i < oldEnd; ++i) {
            for (Bucket curr = oldBuckets[i]; curr != null; curr = curr._next) {
                ++symbolsSeen;
                Name symbol = curr._name;
                int hash = symbol.hashCode();
                int ix = (hash & _mainHashMask);
                int val = _mainHash[ix];
                if (_mainNames[ix] == null) { // no primary entry?
_mainHash[ix] = (val >>> 8);
                    _mainNames[ix] = symbol;
                } else { // nope, it's a collision, need to spill over
                    ++_collCount;
                    int bucket = val & 0xFF;
                    if (bucket == 0) { // first spill over?
                        if (_collEnd <= LAST_VALID_BUCKET) { // yup, still unshared bucket
                            bucket = _collEnd;
                            ++_collEnd;
                            // need to expand?
                            if (bucket >= _collList.length) {
                                expandCollision();
                            }
                        } else { // nope, have to share... let's find shortest?
                            bucket = findBestBucket();
                        }
                        // Need to mark the entry... and the spill index is 1-based
                        _mainHash[ix] = (val & ~0xFF) | (bucket + 1);
                    } else {
                        --bucket; // 1-based index in value
                    }
                    // And then just need to link the new bucket entry in
                    Bucket newB = new Bucket(symbol, _collList[bucket]);
                    _collList[bucket] = newB;
                    maxColl = Math.max(maxColl, newB.length());
                }
            } // for (... buckets in the chain ...)
        } // for (... list of bucket heads ... )

        _longestCollisionList = maxColl;
        
        if (symbolsSeen != _count) { // sanity check
            throw new RuntimeException("Internal error: count after rehash "+symbolsSeen+"; should be "+_count);
        }
    }