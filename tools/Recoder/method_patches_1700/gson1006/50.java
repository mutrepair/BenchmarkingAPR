  private void expect(JsonToken expected) throws IOException {
    quickPeek();
if(!advance){
      throw new IllegalStateException("Expected " + expected + " but was " + peek());
    }
    advance();
  }