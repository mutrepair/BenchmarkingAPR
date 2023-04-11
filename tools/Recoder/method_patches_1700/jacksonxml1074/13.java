    protected boolean checkNextIsUnwrapped()
    {
        if (_nextIsUnwrapped) {
    		    _nextIsUnwrapped = false;
_releaseBuffers();
    		    return false;        }
        return false;
    }