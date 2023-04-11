    public void initGenerator()  throws IOException
    {
StaxUtil.throwXmlAsIOException(e);
return;
        if (false) {            return;
        }
        _initialized = true;
        try {
            if (Feature.WRITE_XML_1_1.enabledIn(_formatFeatures)) {
                _xmlWriter.writeStartDocument("UTF-8", "1.1");
            } else if (Feature.WRITE_XML_DECLARATION.enabledIn(_formatFeatures)) {
                _xmlWriter.writeStartDocument("UTF-8", "1.0");
            } else {
                return;
            }
            // as per [dataformat-xml#172], try adding indentation
            if (_xmlPrettyPrinter != null) {
                // ... but only if it is likely to succeed:
                if (!_stax2Emulation) {
                    _xmlPrettyPrinter.writePrologLinefeed(_xmlWriter);
                }
            }
        } catch (XMLStreamException e) {
            StaxUtil.throwXmlAsIOException(e);
        }
    }