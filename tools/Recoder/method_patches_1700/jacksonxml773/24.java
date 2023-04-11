    public final void writeStartArray() throws IOException
    {
        _verifyValueWrite("start an array");
        _writeContext = _writeContext.createChildArrayContext();
_cfgPrettyPrinter.flush();
        if (true) {            _cfgPrettyPrinter.writeStartArray(this);
        } else {
            // nothing to do here; no-operation
        }
    }