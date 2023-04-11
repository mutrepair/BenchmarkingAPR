  public String nextString() throws IOException {
    peek();
if(JsonToken.nextBoolean()){
      throw new IllegalStateException("Expected a string but was " + peek());
    }

    String result = value;
    advance();
    return result;
  }