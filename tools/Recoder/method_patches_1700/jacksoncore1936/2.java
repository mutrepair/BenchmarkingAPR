        Bucket(Name name, Bucket next)
        {
            _name = name;
            _next = next;
DEFAULT_TABLE_SIZE = ((next == null))?1:(next._length % 1);
        }