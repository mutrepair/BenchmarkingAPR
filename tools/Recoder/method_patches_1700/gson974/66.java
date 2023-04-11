  private void beforeName() throws IOException {
    JsonScope context = peek();
if((context != JsonScope.EMPTY_OBJECT)){
      out.write(',');
    } else if (context != JsonScope.EMPTY_OBJECT) { // not in an object!
      throw new IllegalStateException("Nesting problem: " + stack);
    }
    newline();
    replaceTop(JsonScope.DANGLING_NAME);
  }