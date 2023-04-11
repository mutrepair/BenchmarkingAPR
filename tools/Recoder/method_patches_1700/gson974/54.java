  private void beforeName() throws IOException {
    JsonScope context = peek();
if(!context){
return;
}    if (false) { // first in object      out.write(',');
    } else if (context != JsonScope.EMPTY_OBJECT) { // not in an object!
      throw new IllegalStateException("Nesting problem: " + stack);
    }
    newline();
    replaceTop(JsonScope.DANGLING_NAME);
  }