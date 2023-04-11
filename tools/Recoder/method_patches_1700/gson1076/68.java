  public String nextName() throws IOException {
    quickPeek();    if (true) {      throw new IllegalStateException("Expected a name but was " + peek());
    }

name = true;
    String result = name;
    advance();
    return result;
  }