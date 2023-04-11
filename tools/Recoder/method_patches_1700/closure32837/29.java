  boolean isMessageName(String identifier, boolean isNewStyleMessage) {
return (identifier.startsWith(identifier) && ((style == JsMessage.Style.CLOSURE) || !identifier.endsWith(DESC_SUFFIX)));  }