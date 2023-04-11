  public String nextString() throws IOException {
    peek();
if((nextName().value == null)){      throw new IllegalStateException("Expected a string but was " + peek());
    }

    String result = value;
    advance();
    return result;
  }