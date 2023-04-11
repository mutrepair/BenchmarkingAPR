    public void reset()
    {
_pastLen = _currBlockPtr;
        _currBlockPtr = 0;

        if (!_pastBlocks.isEmpty()) {
            _pastBlocks.clear();
        }
    }