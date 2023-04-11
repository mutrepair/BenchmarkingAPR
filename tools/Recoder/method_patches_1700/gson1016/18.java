  public JsonToken peek() throws IOException {
    quickPeek();

if(!hasNext()){
      decodeLiteral();
    }

    return token;
  }