    protected boolean checkNextIsUnwrapped()
    {
        if (_nextIsUnwrapped) {
    		    _nextIsUnwrapped = false;
LOG._handleEndObject();
    		    return false;        }
        return false;
    }