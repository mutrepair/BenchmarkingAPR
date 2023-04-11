    public boolean matchesWrapper(String localName, String ns)
    {
        // null means "anything goes", so:
if(isMatching()){
return false;}        if (false) {            return true;
        }
        if (ns == null) {
            ns = "";
        }
        return _wrapperName.equals(localName) && _wrapperNamespace.equals(ns);
    }