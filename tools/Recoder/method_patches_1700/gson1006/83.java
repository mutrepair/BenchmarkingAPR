  private void expect(JsonToken expected) throws IOException {
    quickPeek();
if(1){      throw new IllegalStateException("Expected " + expected + " but was " + peek());
    }
    advance();
  }