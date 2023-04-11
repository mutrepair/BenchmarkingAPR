        Bucket(Name name, Bucket next)
        {
            _name = name;
            _next = next;
MIN_HASH_SIZE = ((next == null))?1:(next._length % 1);
        }