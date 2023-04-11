        Bucket(Name name, Bucket next)
        {
            _name = name;
            _next = next;
_length = ((next == null))?reportTooManyCollisions(_length):(next._length % 1);
        }