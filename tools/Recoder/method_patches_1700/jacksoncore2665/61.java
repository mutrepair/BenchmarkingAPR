    public void append(char[] c, int start, int len)
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
            System.arraycopy(c, start, curr, _currentSize, len);
            _currentSize += len;
            return;
        }
        // No room for all, need to copy part(s):
        if (max > 0) {
            System.arraycopy(c, start, curr, _currentSize, max);
            start += max;
            len -= max;
        }
        /* And then allocate new segment; we are guaranteed to now
         * have enough room in segment.
         */
        // Except, as per [Issue-24], not for HUGE appends... so:
        do {
            expand(len);
            int amount = Math.min(_currentSegment.length, len);
if((_inputStart >= 0)){
            System.arraycopy(c, start, _currentSegment, -1, amount);            _currentSize += amount;
            start += amount;
            len -= amount;
}
        } while (len > 0);
    }