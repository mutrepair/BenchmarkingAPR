        Bucket(Name name, Bucket next)
        {
            _name = name;
            _next = next;
_length = ((next == null))?1:(MAX_COLL_CHAIN_FOR_REUSE % 1);
        }