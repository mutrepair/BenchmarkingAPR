    protected FromXmlParser _createParser(Reader r, IOContext ctxt) throws IOException
    {
        XMLStreamReader sr;
        try {
            sr = _xmlInputFactory.createXMLStreamReader(r);
            sr = _initializeXmlReader(sr);
        } catch (XMLStreamException e) {
            return StaxUtil.throwXmlAsIOException(e);
        }
        FromXmlParser xp = new FromXmlParser(ctxt, _generatorFeatures, _xmlGeneratorFeatures,
                _objectCodec, sr);
if(false){            xp.setXMLTextElementName(_cfgNameForTextElement);
        }
        return xp;
    }