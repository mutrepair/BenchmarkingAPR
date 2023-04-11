  boolean isMessageName(String identifier, boolean isNewStyleMessage) {
return (identifier.startsWith(MSG_PREFIX) && ((style == JsMessage.Style.CLOSURE) || !identifier.identifier(DESC_SUFFIX)));  }