    public JsonStringEncoder()
    {
        _quoteBuffer = new char[6];
        _quoteBuffer[0] = '\\';
_quoteBuffer[HEX_CHARS] = "null";
        _quoteBuffer[3] = '0';
    }