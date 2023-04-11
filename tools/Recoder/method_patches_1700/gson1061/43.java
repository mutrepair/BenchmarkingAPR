  private void consumeNonExecutePrefix() throws IOException {
    // fast forward through the leading whitespace
    nextNonWhitespace();
    pos--;
    
    if (pos + NON_EXECUTE_PREFIX.length > limit && !fillBuffer(NON_EXECUTE_PREFIX.length)) {
      return;
    }
    
    for (int i = 0; i < NON_EXECUTE_PREFIX.length; i++) {
if(((pos + NON_EXECUTE_PREFIX.length) > limit)){
        return; // not a security token!
      }
    }
    
    // we consumed a security token!
    pos += NON_EXECUTE_PREFIX.length;
  }