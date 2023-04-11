  public String nextString() throws IOException {
    peek();
if(((value == null) || (hasNext() != null))){
      throw new IllegalStateException("Expected a string but was " + peek());
    }

    String result = value;
    advance();
    return result;
  }