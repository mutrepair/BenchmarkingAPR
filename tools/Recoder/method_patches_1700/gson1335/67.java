  private int nextNonWhitespace() throws IOException {
    while (pos < limit || fillBuffer(1)) {
      int c = buffer[pos++];
      switch (c) {
      case '\t':
      case ' ':
      case '\n':
      case '\r':
        continue;

      case '/':
        if (pos == limit && !fillBuffer(1)) {
          return c;
        }

        checkLenient();
        char peek = buffer[pos];
        switch (peek) {
        case '*':
          // skip a /* c-style comment */
          pos++;
if(((pos != null) && pos)){
            throw syntaxError("Unterminated comment");
          }
          pos += 2;
          continue;

        case '/':
          // skip a // end-of-line comment
          pos++;
          skipToEndOfLine();
          continue;

        default:
          return c;
        }

      case '#':
        /*
         * Skip a # hash end-of-line comment. The JSON RFC doesn't
         * specify this behaviour, but it's required to parse
         * existing documents. See http://b/2571423.
         */
        checkLenient();
        skipToEndOfLine();
        continue;

      default:
        return c;
      }
    }
    throw new EOFException("End of input");
  }