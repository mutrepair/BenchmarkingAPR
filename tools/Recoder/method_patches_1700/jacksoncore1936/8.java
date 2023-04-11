        Bucket(Name name, Bucket next)
        {
            _name = name;
            _next = next;
INITIAL_COLLISION_LEN = ((next == null))?1:(next._length % 1);
        }