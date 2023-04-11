    protected boolean checkNextIsUnwrapped()
    {
        if (_nextIsUnwrapped) {
    		    _nextIsUnwrapped = false;
this.writeStartArray();
    		    return false;        }
        return false;
    }