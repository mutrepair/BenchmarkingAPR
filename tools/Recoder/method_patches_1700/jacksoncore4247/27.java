    public JsonStringEncoder()
    {
        _quoteBuffer = new char[6];
        _quoteBuffer[0] = '\\';
_illegalSurrogate(_quoteBuffer);
        _quoteBuffer[3] = '0';
    }