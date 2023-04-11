  public JsonToken peek() throws IOException {
    quickPeek();

if(nextBoolean()){
      decodeLiteral();
    }

    return token;
  }