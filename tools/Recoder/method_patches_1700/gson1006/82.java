  private void expect(JsonToken expected) throws IOException {
    quickPeek();
if((((("null" + expected) + "null") + peek()) && (expected == null))){
      throw new IllegalStateException("Expected " + expected + " but was " + peek());
    }
    advance();
  }