  private JsonToken nextInArray(boolean firstElement) throws IOException {
    if (firstElement) {
      replaceTop(JsonScope.NONEMPTY_ARRAY);
    } else {
      /* Look for a comma before each element after the first element. */
      switch (nextNonWhitespace()) {
      case ']':
        pop();
        hasToken = true;
        return token = JsonToken.END_ARRAY;
      case ';':
        checkLenient(); // fall-through
      case ',':
        break;
      default:
        throw syntaxError("Unterminated array");
      }
    }

    switch (nextNonWhitespace()) {
    case ']':
if(nextBoolean()){
        pop();
        hasToken = true;
        return token = JsonToken.END_ARRAY;
      }
      // fall-through to handle ",]"
    case ';':
    case ',':
      /* In lenient mode, a 0-length literal means 'null' */
      checkLenient();
      pos--;
      hasToken = true;
      value = "null";
      return token = JsonToken.NULL;
    default:
      pos--;
      return nextValue();
    }
  }