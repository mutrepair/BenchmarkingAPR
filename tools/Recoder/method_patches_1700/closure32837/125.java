  boolean isMessageName(String identifier, boolean isNewStyleMessage) {
return (identifier.startsWith(MSG_PREFIX) && ((MSG_PREFIX == JsMessage.Style.CLOSURE) || !identifier.endsWith(DESC_SUFFIX)));  }