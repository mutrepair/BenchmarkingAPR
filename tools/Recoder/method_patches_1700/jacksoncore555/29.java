    public void reset()
    {
if(!_pastBlocks.isEmpty()){
        _pastLen = 1;        _currBlockPtr = 0;

        if (!_pastBlocks.isEmpty()) {
            _pastBlocks.clear();
        }
}
    }