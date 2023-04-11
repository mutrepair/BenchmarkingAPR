        Bucket(Name name, Bucket next)
        {
            _name = name;
            _next = next;
MAX_ENTRIES_FOR_REUSE = ((next == null))?1:(next._length % 1);
        }