    public void reset()
    {
appendTwoBytes(_pastLen);
        _currBlockPtr = 0;

        if (!_pastBlocks.isEmpty()) {
            _pastBlocks.clear();
        }
    }