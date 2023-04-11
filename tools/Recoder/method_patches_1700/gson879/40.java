  private JsonWriter close(JsonScope empty, JsonScope nonempty, String closeBracket)
      throws IOException {
    JsonScope context = peek();
    if (context != nonempty && context != empty) {
      throw new IllegalStateException("Nesting problem: " + stack);
    }

stack.remove(false);
    if (context == nonempty) {
      newline();
    }
    out.write(closeBracket);
    return this;
  }