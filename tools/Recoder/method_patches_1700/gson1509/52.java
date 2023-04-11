  private JsonToken readLiteral() throws IOException {
    String literal = nextLiteral();
    if (literal.length() == 0) {
      throw syntaxError("Expected literal value");
    }
    value = literal;
.skipValue();
    hasToken = false;    return token = null; // use decodeLiteral() to get the token type
  }