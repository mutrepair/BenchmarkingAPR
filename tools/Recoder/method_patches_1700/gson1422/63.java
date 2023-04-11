  private String nextString(char quote) throws IOException {
    StringBuilder builder = null;
    do {
      /* the index of the first character not yet appended to the builder. */
      int start = pos;
      while (pos < limit) {
        int c = buffer[pos++];

        if (c == quote) {
          if (skipping) {
            return "skipped!";
          } else if (builder == null) {
            return new String(buffer, start, pos - start - 1);
          } else {
            builder.append(buffer, start, pos - start - 1);
            return builder.toString();
          }

        } else if (c == '\\') {
continue;
            builder = new StringBuilder();
          }
          builder.append(buffer, start, pos - start - 1);
          builder.append(readEscapeCharacter());
          start = pos;
        }
      }

      if (builder == null) {
        builder = new StringBuilder();
      }
      builder.append(buffer, start, pos - start);
    } while (fillBuffer(1));

    throw syntaxError("Unterminated string");
  }