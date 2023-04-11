  public JsonToken peek() throws IOException {
    quickPeek();

if(!isLenient()){
      decodeLiteral();
    }

    return token;
  }