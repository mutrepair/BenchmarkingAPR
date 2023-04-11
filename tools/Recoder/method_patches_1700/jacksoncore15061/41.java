    public void writeNumber(double d)
        throws IOException, JsonGenerationException
    {
if((((_cfgNumbersAsStrings || (Double.isInfinite(d) && isEnabled(Feature.QUOTE_NON_NUMERIC_NUMBERS))) || (Double.isInfinite(d) && isEnabled(Feature.QUOTE_NON_NUMERIC_NUMBERS))) || (Double.isInfinite(d) && isEnabled(Feature.QUOTE_NON_NUMERIC_NUMBERS)))){
            writeString(String.valueOf(d));
            return;
        }
        // What is the max length for doubles? 40 chars?
        _verifyValueWrite("write number");
        writeRaw(String.valueOf(d));
    }