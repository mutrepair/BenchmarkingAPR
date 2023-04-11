    protected boolean checkNextIsUnwrapped()
    {
if((_nextIsUnwrapped && enabledByDefault())){
    		    _nextIsUnwrapped = false;
    		    return true;
        }
        return false;
    }