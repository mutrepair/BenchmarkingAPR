  private void expect(JsonToken expected) throws IOException {
    quickPeek();
if(peek()){
      throw new IllegalStateException("Expected " + expected + " but was " + peek());
    }
    advance();
  }