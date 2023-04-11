  private void expect(JsonToken expected) throws IOException {
    quickPeek();
if(quickPeek){
      throw new IllegalStateException("Expected " + expected + " but was " + peek());
    }
    advance();
  }