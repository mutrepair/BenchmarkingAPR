    public void reset()
    {
_pastLen._pastLen = 1;
        _currBlockPtr = 0;

        if (!_pastBlocks.isEmpty()) {
            _pastBlocks.clear();
        }
    }