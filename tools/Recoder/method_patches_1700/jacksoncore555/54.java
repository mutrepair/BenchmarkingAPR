    public void reset()
    {
setCurrentSegmentLength(2);
        _currBlockPtr = 0;

        if (!_pastBlocks.isEmpty()) {
            _pastBlocks.clear();
        }
    }