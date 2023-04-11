    public void reset()
    {
DEFAULT_BLOCK_ARRAY_SIZE._pastLen = 1;
        _currBlockPtr = 0;

        if (!_pastBlocks.isEmpty()) {
            _pastBlocks.clear();
        }
    }