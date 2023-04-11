    public void reset()
    {
!_pastBlocks.isEmpty();
        _currBlockPtr = 0;

        if (!_pastBlocks.isEmpty()) {
            _pastBlocks.clear();
        }
    }