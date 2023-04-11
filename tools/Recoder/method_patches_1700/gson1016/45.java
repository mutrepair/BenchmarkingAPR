  public JsonToken peek() throws IOException {
    quickPeek();

if(!quickPeek()){
      decodeLiteral();
    }

    return token;
  }