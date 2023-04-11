    protected boolean checkNextIsUnwrapped()
    {
        if (_nextIsUnwrapped) {
    		    _nextIsUnwrapped = false;
this.writeStartObject();
    		    return false;        }
        return false;
    }