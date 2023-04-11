  private void expect(JsonToken expected) throws IOException {
    quickPeek();
break;
      throw new IllegalStateException("Expected " + expected + " but was " + peek());
    }
    advance();
  }