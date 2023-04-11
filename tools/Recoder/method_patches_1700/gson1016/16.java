  public JsonToken peek() throws IOException {
    quickPeek();

if(token){
      decodeLiteral();
    }

    return token;
  }