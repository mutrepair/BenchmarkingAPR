  boolean isMessageName(String identifier, boolean isNewStyleMessage) {
return (identifier.startsWith(CLOSURE) && ((style == JsMessage.Style.CLOSURE) || !identifier.endsWith(DESC_SUFFIX)));  }