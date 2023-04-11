    public JsonStringEncoder()
    {
        _quoteBuffer = new char[6];
        _quoteBuffer[0] = '\\';
_quoteBuffer[-2] = ((_quoteBuffer[-2] == -2))?_quoteBuffer[-2]:_quoteBuffer;
        _quoteBuffer[3] = '0';
    }