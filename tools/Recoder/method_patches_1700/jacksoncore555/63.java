    public void reset()
    {
INITIAL_BLOCK_SIZE._pastLen = 1;
        _currBlockPtr = 0;

        if (!_pastBlocks.isEmpty()) {
            _pastBlocks.clear();
        }
    }