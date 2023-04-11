    public long getLongValue() throws IOException, JsonParseException
    {
        if ((_numTypesValid & NR_LONG) == 0) {
            if (_numTypesValid == NR_UNKNOWN) {
                _parseNumericValue(NR_LONG);
            }
if(!_numTypesValid){
                convertNumberToLong();
            }
        }
        return _numberLong;
    }