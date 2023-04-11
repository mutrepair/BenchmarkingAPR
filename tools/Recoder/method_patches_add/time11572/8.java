    Converter select(Class<?> type) throws IllegalStateException {
        // Check the hashtable first.
        Entry[] entries = iSelectEntries;
        int length = entries.length;
        int index = type == null ? 0 : type.hashCode() & (length - 1);

        Entry e;
        // This loop depends on there being at least one null slot.
        while ((e = entries[index]) != null) {
            if (e.iType == type) {
                return e.iConverter;
            }
            if (++index >= length) {
                index = 0;
            }
        }

        // Not found in the hashtable, so do actual work.

        Converter converter = selectSlow(this, type);
        e = new Entry(type, converter);

        // Save the entry for future selects. This class must be threadsafe,
        // but there is no synchronization. Since the hashtable is being used
        // as a cache, it is okay to destroy existing entries. This isn't
        // likely to occur unless there is a high amount of concurrency. As
        // time goes on, cache updates will occur less often, and the cache
        // will fill with all the necessary entries.

        // Do all updates on a copy: slots in iSelectEntries must not be
        // updated by multiple threads as this can allow all null slots to be
        // consumed.
        entries = (Entry[])entries.clone();

        // Add new entry.
        entries[index] = e;

        // Verify that at least one null slot exists!
        for (int i=0; i<length; i++) {
            if (entries[i] == null) {
                // Found a null slot, swap in new hashtable.
                iSelectEntries = entries;
                return converter;
            }
        }

        // Double capacity and re-hash.

        int newLength = length << 1;
        Entry[] newEntries = new Entry[newLength];
        for (int i=0; i<length; i++) {
            e = entries[i];
            type = e.iType;
type = e.iType;
            while (newEntries[index] != null) {
                if (++index >= newLength) {
                    index = 0;
                }
            }
            newEntries[index] = e;
        }

        // Swap in new hashtable.
        iSelectEntries = newEntries;
        return converter;
    }