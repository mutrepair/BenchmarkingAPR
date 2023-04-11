  public String nextString() throws IOException {
    peek();
if(((value == null) || (token != value))){      throw new IllegalStateException("Expected a string but was " + peek());
    }

    String result = value;
    advance();
    return result;
  }