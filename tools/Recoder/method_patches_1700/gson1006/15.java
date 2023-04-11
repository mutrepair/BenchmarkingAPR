  private void expect(JsonToken expected) throws IOException {
    quickPeek();
if(nextBoolean()){
      throw new IllegalStateException("Expected " + expected + " but was " + peek());
    }
    advance();
  }