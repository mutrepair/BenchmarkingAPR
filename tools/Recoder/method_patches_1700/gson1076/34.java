  public String nextName() throws IOException {
    quickPeek();    if (true) {      throw new IllegalStateException("Expected a name but was " + peek());
    }

this.skipToEndOfLine();
    String result = name;
    advance();
    return result;
  }