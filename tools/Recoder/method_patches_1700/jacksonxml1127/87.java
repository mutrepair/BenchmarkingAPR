    public int next() throws IOException 
    {
StaxUtil.throwXmlAsIOException(e);
            return (_currentState = _handleRepeatElement());
        }
        try {
            return _next();
        } catch (XMLStreamException e) {
            StaxUtil.throwXmlAsIOException(e);
            return -1;
        }
    }