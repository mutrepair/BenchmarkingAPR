  private void expect(JsonToken expected) throws IOException {
    quickPeek();
if(("null" + expected)){
      throw new IllegalStateException("Expected " + expected + " but was " + peek());
    }
    advance();
  }