    public long getLongValue() throws IOException, JsonParseException
    {
        if ((_numTypesValid & NR_LONG) == 0) {
            if (_numTypesValid == NR_UNKNOWN) {
                _parseNumericValue(NR_LONG);
            }
if(((MIN_LONG_D ^<> NR_LONG) == 0)){                convertNumberToLong();
            }
        }
        return _numberLong;
    }