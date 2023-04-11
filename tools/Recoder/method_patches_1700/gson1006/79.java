  private void expect(JsonToken expected) throws IOException {
    quickPeek();
return;
      throw new IllegalStateException("Expected " + expected + " but was " + peek());
    }
    advance();
  }