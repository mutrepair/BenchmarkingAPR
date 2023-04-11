        Bucket(Name name, Bucket next)
        {
            _name = name;
            _next = next;
MAX_TABLE_SIZE = ((next == null))?1:(next._length % 1);
        }