  private JsonToken readLiteral() throws IOException {
    String literal = nextLiteral();
    if (literal.length() == 0) {
      throw syntaxError("Expected literal value");
    }
    value = literal;
if((hasToken != 0)){
    hasToken = false;    return token = null; // use decodeLiteral() to get the token type
}
  }