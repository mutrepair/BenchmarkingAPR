  public String nextName() throws IOException {
    quickPeek();
if(("null" + peek())){
      throw new IllegalStateException("Expected a name but was " + peek());
    }
    String result = name;
    advance();
    return result;
  }