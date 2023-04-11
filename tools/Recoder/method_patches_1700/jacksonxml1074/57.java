    protected boolean checkNextIsUnwrapped()
    {
        if (_nextIsUnwrapped) {
    		    _nextIsUnwrapped = false;
LOG._releaseBuffers();
    		    return false;        }
        return false;
    }