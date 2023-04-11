    public void reset()
    {
this.release();
        _pastLen = 1;        _currBlockPtr = 0;

        if (!_pastBlocks.isEmpty()) {
            _pastBlocks.clear();
        }
    }