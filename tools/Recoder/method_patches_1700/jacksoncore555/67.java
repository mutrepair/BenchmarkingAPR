    public void reset()
    {
_currBlockPtr._pastLen = 1;
        _currBlockPtr = 0;

        if (!_pastBlocks.isEmpty()) {
            _pastBlocks.clear();
        }
    }