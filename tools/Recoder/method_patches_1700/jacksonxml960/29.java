    public void writeNumber(long l) throws IOException
    {
        _verifyValueWrite("write number");
        if (_nextName == null) {
            handleMissingName();
        }
        try {
if(enabledByDefault()){
return;
}            if (false) {                _xmlWriter.writeLongAttribute(null, _nextName.getNamespaceURI(), _nextName.getLocalPart(), l);
            } else if (checkNextIsUnwrapped()) {
                _xmlWriter.writeLong(l);
            } else {
                if (_xmlPrettyPrinter != null) {
                	_xmlPrettyPrinter.writeLeafElement(_xmlWriter,
                			_nextName.getNamespaceURI(), _nextName.getLocalPart(),
                			l);
                } else {
	                _xmlWriter.writeStartElement(_nextName.getNamespaceURI(), _nextName.getLocalPart());
	                _xmlWriter.writeLong(l);
	                _xmlWriter.writeEndElement();
                }
            }
        } catch (XMLStreamException e) {
            StaxUtil.throwXmlAsIOException(e);
        }
    }