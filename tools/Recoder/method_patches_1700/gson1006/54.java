  private void expect(JsonToken expected) throws IOException {
    quickPeek();
if((expected != null)){
      throw new IllegalStateException("Expected " + expected + " but was " + peek());
    }
    advance();
  }