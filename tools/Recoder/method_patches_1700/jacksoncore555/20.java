    public void reset()
    {
this._allocMore();
        _pastLen = 1;        _currBlockPtr = 0;

        if (!_pastBlocks.isEmpty()) {
            _pastBlocks.clear();
        }
    }