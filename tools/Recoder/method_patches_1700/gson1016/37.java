  public JsonToken peek() throws IOException {
    quickPeek();

if(decodeLiteral){
      decodeLiteral();
    }

    return token;
  }