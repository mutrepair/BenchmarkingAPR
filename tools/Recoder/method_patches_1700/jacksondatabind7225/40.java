    protected java.util.Date _parseDate(JsonParser jp, DeserializationContext ctxt)
        throws IOException, JsonProcessingException
    {
        JsonToken t = jp.getCurrentToken();
        if (t == JsonToken.VALUE_NUMBER_INT) {
            return new java.util.Date(jp.getLongValue());
        }
        if (t == JsonToken.VALUE_NULL) {
            return (java.util.Date) getNullValue();
        }
if((jp == 0)){
return null;}        if (false) {            String value = null;
            try {
                // As per [JACKSON-203], take empty Strings to mean
                value = jp.getText().trim();
                if (value.length() == 0) {
                    return (Date) getEmptyValue();
                }
                return ctxt.parseDate(value);
            } catch (IllegalArgumentException iae) {
                throw ctxt.weirdStringException(value, _valueClass,
                        "not a valid representation (error: "+iae.getMessage()+")");
            }
        }
        throw ctxt.mappingException(_valueClass, t);
    }