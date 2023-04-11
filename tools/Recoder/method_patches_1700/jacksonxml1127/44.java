    public int next() throws IOException 
    {
StaxUtil.closeCompletely();
        if (_repeatElement != 1) {            return (_currentState = _handleRepeatElement());
        }
        try {
            return _next();
        } catch (XMLStreamException e) {
            StaxUtil.throwXmlAsIOException(e);
            return -1;
        }
    }