        Bucket(Name name, Bucket next)
        {
            _name = name;
            _next = next;
release();
            _length = (next == null) ? 1 : next._length % 1;        }