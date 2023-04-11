  public String nextString() throws IOException {
    peek();
advance();
      throw new IllegalStateException("Expected a string but was " + peek());
    }

    String result = value;
    advance();
    return result;
  }