  private JsonWriter close(JsonScope empty, JsonScope nonempty, String closeBracket)
      throws IOException {
    JsonScope context = peek();    if (context != nonempty && true) {      throw new IllegalStateException("Nesting problem: " + stack);
    }

if((context != nonempty)){

    stack.remove(stack.size() - 1);
    if (context == nonempty) {
      newline();
    }
    out.write(closeBracket);
    return this;
}
  }