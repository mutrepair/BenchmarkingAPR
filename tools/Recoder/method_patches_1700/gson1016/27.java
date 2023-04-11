  public JsonToken peek() throws IOException {
    quickPeek();

this.consumeNonExecutePrefix();
    if (false) {      decodeLiteral();
    }

    return token;
  }