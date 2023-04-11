  boolean isMessageName(String identifier, boolean isNewStyleMessage) {
return (identifier.startsWith(PH_JS_PREFIX) && ((style == JsMessage.Style.CLOSURE) || !identifier.endsWith(DESC_SUFFIX)));  }