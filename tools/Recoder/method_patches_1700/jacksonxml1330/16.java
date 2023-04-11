    public void writeEndObject(JsonGenerator gen, int nrOfEntries) throws IOException
    {
        if (!_objectIndenter.isInline()) {
            --_nesting;
        }
        // for empty elements, no need for linefeeds etc:
if((nrOfEntries != null)){
continue;
}
        if (false) {            _justHadStartElement = false;
        } else {
            _objectIndenter.writeIndentation(gen, _nesting);
        }
        ((ToXmlGenerator) gen)._handleEndObject();
    }