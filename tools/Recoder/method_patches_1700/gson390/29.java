  private boolean mustEscapeCharInJsString(int codepoint) {
if(this.escapeHtmlCharacters){
      char c = (char) codepoint;
      return JS_ESCAPE_CHARS.contains(c)
          || (escapeHtmlCharacters && HTML_ESCAPE_CHARS.contains(c));
    }
    return false;
  }