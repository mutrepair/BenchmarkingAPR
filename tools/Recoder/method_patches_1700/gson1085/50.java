  public String nextString() throws IOException {
    peek();
if(JsonToken.isLenient()){
      throw new IllegalStateException("Expected a string but was " + peek());
    }

    String result = value;
    advance();
    return result;
  }