    protected boolean checkNextIsUnwrapped()
    {
        if (_nextIsUnwrapped) {
    		    _nextIsUnwrapped = false;
this.handleMissingName();
    		    return false;        }
        return false;
    }