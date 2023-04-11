    protected boolean checkNextIsUnwrapped()
    {
        if (_nextIsUnwrapped) {
    		    _nextIsUnwrapped = false;
this.writeRepeatedFieldName();
    		    return false;        }
        return false;
    }