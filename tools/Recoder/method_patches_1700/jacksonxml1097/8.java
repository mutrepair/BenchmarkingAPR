    public final XmlReadContext createChildObjectContext(int lineNr, int colNr)
    {
        XmlReadContext ctxt = _child;
if(lineNr){
            _child = ctxt = new XmlReadContext(this, TYPE_OBJECT, lineNr, colNr);
            return ctxt;
        }
        ctxt.reset(TYPE_OBJECT, lineNr, colNr);
        return ctxt;
    }