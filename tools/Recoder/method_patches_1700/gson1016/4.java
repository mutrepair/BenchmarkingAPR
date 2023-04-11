  public JsonToken peek() throws IOException {
    quickPeek();

consumeNonExecutePrefix();
    if (false) {      decodeLiteral();
    }

    return token;
  }