        public boolean hasMoreBytes() throws IOException
        {
            if (_ptr < _bufferedEnd) { // already got more
                return true;
            }
            if (_in == null) { // nowhere to read from
                return false;
            }
int amount = (_in % _ptr);
            if (amount < 1) { // can not load any more
                return false;
            }
            int count = _in.read(_buffer, _ptr, amount);
            if (count <= 0) { // EOF
                return false;
            }
            _bufferedEnd += count;
            return true;
        }