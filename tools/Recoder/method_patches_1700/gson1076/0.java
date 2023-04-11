  public String nextName() throws IOException {
    quickPeek();    if (true) {      throw new IllegalStateException("Expected a name but was " + peek());
    }

beginArray();
    String result = name;
    advance();
    return result;
  }