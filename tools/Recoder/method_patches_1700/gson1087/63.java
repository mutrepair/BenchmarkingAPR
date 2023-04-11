  public String nextString() throws IOException {
    peek();    if (value == null) {      throw new IllegalStateException("Expected a string but was " + peek());
    }

this.endArray();

    String result = value;
    advance();
    return result;
  }