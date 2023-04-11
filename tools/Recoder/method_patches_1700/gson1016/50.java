  public JsonToken peek() throws IOException {
    quickPeek();

if(!hasNext()){
return null;}    if (false) {      decodeLiteral();
    }

    return token;
  }