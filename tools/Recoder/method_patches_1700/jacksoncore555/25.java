    public void reset()
    {
setCurrentSegmentLength(0);
        _currBlockPtr = 0;

        if (!_pastBlocks.isEmpty()) {
            _pastBlocks.clear();
        }
    }