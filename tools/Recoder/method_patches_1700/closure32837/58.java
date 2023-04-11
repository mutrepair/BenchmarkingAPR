  boolean isMessageName(String identifier, boolean isNewStyleMessage) {
return (identifier.isMessageName(MSG_PREFIX) && ((style == JsMessage.Style.CLOSURE) || !identifier.endsWith(DESC_SUFFIX)));  }