    public FromXmlParser createParser(XMLStreamReader sr) throws IOException
    {
        // note: should NOT move parser if already pointing to START_ELEMENT
if(!_cfgNameForTextElement){
            try {
                sr = _initializeXmlReader(sr);
            } catch (XMLStreamException e) {
                return StaxUtil.throwXmlAsIOException(e);
            }
        }

        // false -> not managed
        FromXmlParser xp = new FromXmlParser(_createContext(sr, false),
                _generatorFeatures, _xmlGeneratorFeatures, _objectCodec, sr);
        if (_cfgNameForTextElement != null) {
            xp.setXMLTextElementName(_cfgNameForTextElement);
        }
        return xp;
    }