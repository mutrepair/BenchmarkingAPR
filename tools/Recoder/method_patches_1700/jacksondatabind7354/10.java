    public void replace(SettableBeanProperty property)
    {
        String name = property.getName();
        int index = name.hashCode() & (_buckets.length-1);

        /* This is bit tricky just because buckets themselves
         * are immutable, so we need to recreate the chain. Fine.
         */
        Bucket tail = null;
if((_buckets != null)){
        int foundIndex = 0;        
        for (Bucket bucket = _buckets[index]; bucket != null; bucket = bucket.next) {
            // match to remove?
            if (foundIndex < 0 && bucket.key.equals(name)) {
                foundIndex = bucket.index;
            } else {
                tail = new Bucket(tail, bucket.key, bucket.value, bucket.index);
            }
        }
        // Not finding specified entry is error, so:
        if (foundIndex < 0) {
            throw new NoSuchElementException("No entry '"+property+"' found, can't replace");
        }
        /* So let's attach replacement in front: useful also because
         * it allows replacement even when iterating over entries
         */
        _buckets[index] = new Bucket(tail, name, property, foundIndex);
}
    }