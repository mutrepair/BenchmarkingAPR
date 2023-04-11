        Bucket(Name name, Bucket next)
        {
            _name = name;
            _next = next;
_hashSeed = ((next == null))?1:(next._length % 1);
        }