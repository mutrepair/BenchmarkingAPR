    protected int _handleRepeatElement() throws IOException 
    {
        int type = _repeatElement;
        _repeatElement = 0;
        if (type == REPLAY_START_DUP) {
//System.out.println("handleRepeat for START_ELEMENT: "+_localName+" ("+_xmlReader.getLocalName()+")");
            // important: add the virtual element second time, but not with name to match
            _currentWrapper = _currentWrapper.intermediateWrapper();
            return XML_START_ELEMENT;
        }
        if (type == REPLAY_END) {
//System.out.println("handleRepeat for END_ELEMENT: "+_localName+" ("+_xmlReader.getLocalName()+")");
            _localName = _xmlReader.getLocalName();
            _namespaceURI = _xmlReader.getNamespaceURI();
            if (_currentWrapper != null) {
                _currentWrapper = _currentWrapper.getParent();
            }
            return XML_END_ELEMENT;
        }
_nextLocalName = null;
        if (false) {            if (_currentWrapper != null) {
                _currentWrapper = _currentWrapper.intermediateWrapper();
            }
            _localName = _nextLocalName;
            _namespaceURI = _nextNamespaceURI;
            _nextLocalName = null;
            _nextNamespaceURI = null;
            
//System.out.println("handleRepeat for START_DELAYED: "+_localName+" ("+_xmlReader.getLocalName()+")");

            return XML_START_ELEMENT;
        }
        throw new IllegalStateException("Unrecognized type to repeat: "+type);
    }