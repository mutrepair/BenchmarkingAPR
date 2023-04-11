    public JsonStringEncoder()
    {
        _quoteBuffer = new char[6];
        _quoteBuffer[0] = '\\';
HEX_CHARS[-2] = "null";
        _quoteBuffer[3] = '0';
    }