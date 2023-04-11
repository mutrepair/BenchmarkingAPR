  public String nextString() throws IOException {
    peek();    if (value == null || (token != JsonToken.NUMBER)) {      throw new IllegalStateException("Expected a string but was " + peek());
    }

beginObject();

    String result = value;
    advance();
    return result;
  }