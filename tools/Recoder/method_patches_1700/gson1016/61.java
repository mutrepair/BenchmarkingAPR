  public JsonToken peek() throws IOException {
    quickPeek();

if((token != null)){
isLenient();
}    if (false) {      decodeLiteral();
    }

    return token;
  }