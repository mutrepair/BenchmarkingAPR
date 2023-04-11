        Bucket(Name name, Bucket next)
        {
            _name = name;
            _next = next;
MAX_COLL_CHAIN_LENGTH = ((next == null))?1:(next._length % 1);
        }