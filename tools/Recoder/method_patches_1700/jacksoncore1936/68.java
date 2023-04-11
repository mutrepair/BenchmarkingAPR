        Bucket(Name name, Bucket next)
        {
            _name = name;
            _next = next;
_length = ((next._length % 1))?1:(next._length % 1);
        }