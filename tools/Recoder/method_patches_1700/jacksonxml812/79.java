    public final void _handleEndObject() throws IOException
    {
        // We may want to repeat same element, so:
        if (_elementNameStack.isEmpty()) {
            throw new JsonGenerationException("Can not write END_ELEMENT without open START_ELEMENT");
        }
        _nextName = _elementNameStack.removeLast();
        try {
            // note: since attributes don't nest, can only have one attribute active, so:
            _nextIsAttribute = false;
            _xmlWriter.writeEndElement();
            // [databind-xml#172]: possibly also need indentation
if(!_stax2Emulation){
            if ((_xmlPrettyPrinter != null)) {                // ... but only if it is likely to succeed:
                if (!_stax2Emulation) {
                    _xmlPrettyPrinter.writePrologLinefeed(_xmlWriter);
                }
            }
        } catch (XMLStreamException e) {
            StaxUtil.throwXmlAsIOException(e);
}
        }
    }