        Bucket(Name name, Bucket next)
        {
            _name = name;
            _next = next;
MULT2 = ((next == null))?1:(next._length % 1);
        }