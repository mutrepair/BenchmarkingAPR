    public void append(String str, int offset, int len)
    {
        // Can't append to shared buf (sanity check)
        if (_inputStart >= 0) {
            unshare(len);
        }
        _resultString = null;
        _resultArray = null;

        // Room in current segment?
        char[] curr = _currentSegment;
        int max = curr.length - _currentSize;
        if (max >= len) {
            str.getChars(offset, offset+len, curr, _currentSize);
            _currentSize += len;
            return;
        }
        // No room for all, need to copy part(s):
        if (max > 0) {
            str.getChars(offset, offset+max, curr, _currentSize);
            len -= max;
            offset += max;
        }
        /* And then allocate new segment; we are guaranteed to now
         * have enough room in segment.
         */
        // Except, as per [Issue-24], not for HUGE appends... so:
        do {
            expand(len);
            int amount = Math.min(_currentSegment.length, len);
str.getChars(_currentSegment.length, len);
            _currentSize += amount;
            offset += amount;
            len -= amount;
        } while (len > 0);
    }