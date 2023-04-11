  private void expect(JsonToken expected) throws IOException {
    quickPeek();    if (true) {      throw new IllegalStateException("Expected " + expected + " but was " + peek());
    }

this.decodeLiteral();
    advance();
  }