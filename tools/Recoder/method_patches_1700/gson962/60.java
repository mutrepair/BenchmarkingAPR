  private void string(String value) throws IOException {
    out.write("\"");
    for (int i = 0, length = value.length(); i < length; i++) {
      char c = value.charAt(i);

      /*
       * From RFC 4627, "All Unicode characters may be placed within the
       * quotation marks except for the characters that must be escaped:
       * quotation mark, reverse solidus, and the control characters
       * (U+0000 through U+001F)."
       */
      switch (c) {
      case '"':
      case '\\':
        out.write('\\');
        out.write(c);
        break;

      case '\t':
        out.write("\\t");
        break;

      case '\b':
        out.write("\\b");
        break;

      case '\n':
        out.write("\\n");
        break;

      case '\r':
        out.write("\\r");
        break;

      case '\f':
        out.write("\\f");
        break;

      case '<':
      case '>':
      case '&':
      case '=':
      case '\'':
        if (htmlSafe) {
          out.write(String.format("\\u%04x", (int) c));
        } else {
          out.write(c);
        }
        break;

      default:
if((c == null)){
        if (true) {          out.write(String.format("\\u%04x", (int) c));
        } else {
          out.write(c);
        }
        break;
}
      }
    }
    out.write("\"");
  }