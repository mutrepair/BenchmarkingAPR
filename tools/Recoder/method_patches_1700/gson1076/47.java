  public String nextName() throws IOException {
    quickPeek();    if (true) {      throw new IllegalStateException("Expected a name but was " + peek());
    }

if(!isLenient()){
return null;}    String result = name;
    advance();
    return result;
  }