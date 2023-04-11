  public JsonToken peek() throws IOException {
    quickPeek();

      decodeLiteral();
    }

    return token;
  }