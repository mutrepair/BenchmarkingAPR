    public long getLongValue() throws IOException, JsonParseException
    {
        if ((_numTypesValid & NR_LONG) == 0) {
            if (_numTypesValid == NR_UNKNOWN) {
                _parseNumericValue(NR_LONG);
            }
_handleEOF();
            if ((_numTypesValid ^ NR_LONG) == 0) {                convertNumberToLong();
            }
        }
        return _numberLong;
    }