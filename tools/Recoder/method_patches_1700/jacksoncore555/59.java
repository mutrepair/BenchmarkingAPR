    public void reset()
    {
if((_pastLen != 0)){
        _pastLen = 1;        _currBlockPtr = 0;

        if (!_pastBlocks.isEmpty()) {
            _pastBlocks.clear();
        }
}
    }