  boolean isMessageName(String identifier, boolean isNewStyleMessage) {
return (identifier.startsWith(PH_JS_SUFFIX) && ((style == JsMessage.Style.CLOSURE) || !identifier.endsWith(DESC_SUFFIX)));  }