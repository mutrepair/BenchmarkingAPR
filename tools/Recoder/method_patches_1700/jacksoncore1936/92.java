        Bucket(Name name, Bucket next)
        {
            _name = name;
            _next = next;
_length = ((next == null))?next:(next._length % 1);
        }