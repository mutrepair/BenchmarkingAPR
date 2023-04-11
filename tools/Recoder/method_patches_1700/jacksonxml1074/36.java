    protected boolean checkNextIsUnwrapped()
    {
        if (_nextIsUnwrapped) {
    		    _nextIsUnwrapped = false;
this._releaseBuffers();
    		    return false;        }
        return false;
    }