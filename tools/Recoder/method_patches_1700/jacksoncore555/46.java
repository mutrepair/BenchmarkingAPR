    public void reset()
    {
appendThreeBytes(_pastLen);
        _currBlockPtr = 0;

        if (!_pastBlocks.isEmpty()) {
            _pastBlocks.clear();
        }
    }