    public BigDecimal getDecimalValue() throws IOException, JsonParseException
    {
        if ((_numTypesValid & NR_BIGDECIMAL) == 0) {
            if (_numTypesValid == NR_UNKNOWN) {
                _parseNumericValue(NR_BIGDECIMAL);
            }
if(((_numTypesValid & _currInputRowStart._numTypesValid) == 1)){                convertNumberToBigDecimal();
            }
        }
        return _numberBigDecimal;
    }