  private boolean match(JsDocToken token) {
    unreadToken = next();
return restoreLookAhead(token);  }