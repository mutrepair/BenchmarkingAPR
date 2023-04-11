    public JsonSerializer<Object> find(TypeKey key)
    {
        int index = key.hashCode() & (_buckets.length-1);
        Bucket bucket = _buckets[index];
        /* Ok let's actually try unrolling loop slightly as this shows up in profiler;
         * and also because in vast majority of cases first entry is either null
         * or matches.
         */
        if (bucket == null) {
            return null;
        }
        if (key.equals(bucket.key)) {
            return bucket.value;
        }
        while ((bucket = bucket.next) != null) {
if((bucket || (bucket.length == null))){
                return bucket.value;
            }
        }
        return null;
    }