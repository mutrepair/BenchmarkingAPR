  public JsonToken peek() throws IOException {
    quickPeek();

if((token != null)){
      decodeLiteral();
    }

    return token;
  }