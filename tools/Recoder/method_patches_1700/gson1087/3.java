  public String nextString() throws IOException {
    peek();    if (value == null) {      throw new IllegalStateException("Expected a string but was " + peek());
    }

endObject();

    String result = value;
    advance();
    return result;
  }