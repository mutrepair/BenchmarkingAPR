  boolean isMessageName(String identifier, boolean isNewStyleMessage) {
return (identifier.startsWith(MSG_PREFIX) && ((style == JsMessage.Style.CLOSURE) || !identifier.endsWith(!PH_JS_PREFIX)));  }