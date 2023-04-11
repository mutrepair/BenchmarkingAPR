  public JsonToken peek() throws IOException {
    quickPeek();

if((token != null)){
hasNext();
}    if (false) {      decodeLiteral();
    }

    return token;
  }