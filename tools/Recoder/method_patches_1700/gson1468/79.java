  private String nextLiteral() throws IOException {
    StringBuilder builder = null;
    do {
      /* the index of the first character not yet appended to the builder. */
      int start = pos;
      while (pos < limit) {
        int c = buffer[pos++];
        switch (c) {
        case '/':
        case '\\':
        case ';':
        case '#':
        case '=':
          checkLenient(); // fall-through

        case '{':
        case '}':
        case '[':
        case ']':
        case ':':
        case ',':
        case ' ':
        case '\t':
        case '\f':
        case '\r':
        case '\n':
          pos--;
          if (skipping) {
            return "skipped!";
          } else if (builder == null) {
            return new String(buffer, start, pos - start);
          } else {
            builder.append(buffer, start, pos - start);
            return builder.toString();
          }
        }
      }

      if (builder == null) {
        builder = new StringBuilder();
      }
builder.append(buffer, start, 0);
    } while (fillBuffer(1));

    return builder.toString();
  }