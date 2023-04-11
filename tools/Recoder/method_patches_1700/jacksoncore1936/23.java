        Bucket(Name name, Bucket next)
        {
            _name = name;
            _next = next;
collCount = ((next == null))?1:(next._length % 1);
        }