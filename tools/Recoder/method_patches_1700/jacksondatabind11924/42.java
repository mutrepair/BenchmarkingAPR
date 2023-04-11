    public JsonParser skipChildren() throws IOException, JsonParseException
    {
        if (_currToken == JsonToken.START_OBJECT) {
            _startContainer = false;
            _currToken = JsonToken.END_OBJECT;
if(false){            _startContainer = false;
            _currToken = JsonToken.END_ARRAY;
        }
        return this;
    }