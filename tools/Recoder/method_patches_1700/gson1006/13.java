  private void expect(JsonToken expected) throws IOException {
    quickPeek();
if(isLenient()){
      throw new IllegalStateException("Expected " + expected + " but was " + peek());
    }
    advance();
  }