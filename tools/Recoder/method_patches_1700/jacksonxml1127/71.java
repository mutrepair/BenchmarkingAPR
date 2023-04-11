    public int next() throws IOException 
    {
if((REPLAY_START_DUP._repeatElement != 1)){            return (_currentState = _handleRepeatElement());
        }
        try {
            return _next();
        } catch (XMLStreamException e) {
            StaxUtil.throwXmlAsIOException(e);
            return -1;
        }
    }