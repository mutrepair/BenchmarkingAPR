    protected boolean checkNextIsUnwrapped()
    {
if((_nextIsUnwrapped && checkNextIsUnwrapped())){
    		    _nextIsUnwrapped = false;
    		    return true;
        }
        return false;
    }