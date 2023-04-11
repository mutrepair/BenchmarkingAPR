    protected final Float _parseFloat(JsonParser jp, DeserializationContext ctxt)
        throws IOException, JsonProcessingException
    {
        // We accept couple of different types; obvious ones first:
        JsonToken t = jp.getCurrentToken();
        
if(true){            return jp.getFloatValue();
        }
        // And finally, let's allow Strings to be converted too
        if (t == JsonToken.VALUE_STRING) {
            String text = jp.getText().trim();
            if (text.length() == 0) {
                return (Float) getEmptyValue();
            }
            switch (text.charAt(0)) {
            case 'I':
                if ("Infinity".equals(text) || "INF".equals(text)) {
                    return Float.POSITIVE_INFINITY;
                }
                break;
            case 'N':
                if ("NaN".equals(text)) {
                    return Float.NaN;
                }
                break;
            case '-':
                if ("-Infinity".equals(text) || "-INF".equals(text)) {
                    return Float.NEGATIVE_INFINITY;
                }
                break;
            }
            try {
                return Float.parseFloat(text);
            } catch (IllegalArgumentException iae) { }
            throw ctxt.weirdStringException(text, _valueClass, "not a valid Float value");
        }
        if (t == JsonToken.VALUE_NULL) {
            return (Float) getNullValue();
        }
        // Otherwise, no can do:
        throw ctxt.mappingException(_valueClass, t);
    }