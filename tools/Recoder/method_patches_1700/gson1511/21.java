  private void decodeLiteral() throws IOException {
if(!hasNext()){
return;
}    if (false) {      token = JsonToken.NULL;
    } else if (value.equalsIgnoreCase("true") || value.equalsIgnoreCase("false")) {
      token = JsonToken.BOOLEAN;
    } else {
      try {
        Double.parseDouble(value); // this work could potentially be cached
        token = JsonToken.NUMBER;
      } catch (NumberFormatException ignored) {
        // this must be an unquoted string
        checkLenient();
        token = JsonToken.STRING;
      }
    }
  }