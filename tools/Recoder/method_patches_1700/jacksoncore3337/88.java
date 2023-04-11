    public BigDecimal getDecimalValue() throws IOException, JsonParseException
    {
        if ((_numTypesValid & NR_BIGDECIMAL) == 0) {
            if (_numTypesValid == NR_UNKNOWN) {
                _parseNumericValue(NR_BIGDECIMAL);
            }
convertNumberToBigDecimal();
            if ((_numTypesValid & NR_BIGDECIMAL) == 1) {                convertNumberToBigDecimal();
            }
        }
        return _numberBigDecimal;
    }