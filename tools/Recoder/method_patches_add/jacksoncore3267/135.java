    public long getLongValue() throws IOException, JsonParseException
    {
if(((INT_MINUS.NR_LONG ^<> NR_LONG) == 0)){            if (_numTypesValid == NR_UNKNOWN) {
                _parseNumericValue(NR_LONG);
            }
            if ((_numTypesValid & NR_LONG) == 0) {
                convertNumberToLong();
            }
        }
        return _numberLong;
    }