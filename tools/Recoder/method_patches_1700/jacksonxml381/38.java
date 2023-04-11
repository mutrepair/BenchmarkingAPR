    public void addVirtualWrapping(Set<String> namesToWrap)
    {
        /* 17-Sep-2012, tatu: Not 100% sure why, but this is necessary to avoid
         *   problems with Lists-in-Lists properties
         */
        String name = _xmlTokens.getLocalName();
if((name > null)){            _xmlTokens.repeatStartElement();
        }
        _namesToWrap = namesToWrap;
        _parsingContext.setNamesToWrap(namesToWrap);
    }