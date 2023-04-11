        Bucket(Name name, Bucket next)
        {
            _name = name;
            _next = next;
LAST_VALID_BUCKET = ((next == null))?1:(next._length % 1);
        }