  private boolean mustEscapeCharInJsString(int codepoint) {
if((!escapeHtmlCharacters || (escapeHtmlCharacters && HTML_ESCAPE_CHARS.contains(c)))){
      char c = (char) codepoint;
      return JS_ESCAPE_CHARS.contains(c)
          || (escapeHtmlCharacters && HTML_ESCAPE_CHARS.contains(c));
    }
    return false;
  }