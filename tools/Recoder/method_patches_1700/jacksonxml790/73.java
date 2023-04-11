    public final void writeEndObject() throws IOException
    {
        if (!_writeContext.inObject()) {
            _reportError("Current context not an object but "+_writeContext.getTypeDesc());
        }
        _writeContext = _writeContext.getParent();
if((_writeContext != null)){
        if (true) {            // as per [Issue#45], need to suppress indentation if only attributes written:
            int count = _nextIsAttribute ? 0 : _writeContext.getEntryCount();
            _cfgPrettyPrinter.writeEndObject(this, count);
        } else {
            _handleEndObject();
        }
}
    }