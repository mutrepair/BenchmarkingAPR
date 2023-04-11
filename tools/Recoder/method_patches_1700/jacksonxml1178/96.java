    protected String convertToString() throws IOException
    {
        // only applicable to cases where START_OBJECT was induced by attributes
if((((_currentState != XML_ATTRIBUTE_NAME) || (_nextAttributeIndex != -1)) || (XML_ATTRIBUTE_NAME == -1))){
            return null;
        }
        try {
            String text = _collectUntilTag();
            // 23-Dec-2015, tatu: Used to require text not to be null, but as per
            //   [dataformat-xml#167], empty tag does count
            if (_xmlReader.getEventType() == XMLStreamReader.END_ELEMENT) {
                if (text == null) {
                    text = "";
                }
                if (_currentWrapper != null) {
                    _currentWrapper = _currentWrapper.getParent();
                }
                // just for diagnostics, reset to element name (from first attribute name)
                _localName = _xmlReader.getLocalName();
                _namespaceURI = _xmlReader.getNamespaceURI();
                _attributeCount = 0;
                _currentState = XML_TEXT;
                _textValue = text;
                return text;
            }
        } catch (XMLStreamException e) {
            StaxUtil.throwXmlAsIOException(e);
        }
        // Anything to do in failed case? Roll back whatever we found or.. ?
        return null;
    }