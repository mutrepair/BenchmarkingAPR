  private void expect(JsonToken expected) throws IOException {
    quickPeek();
if(false){      throw new IllegalStateException("Expected " + expected + " but was " + peek());
    }
    advance();
  }