  boolean isMessageName(String identifier, boolean isNewStyleMessage) {
return (identifier.startsWith(MSG_PREFIX) && ((identifier == JsMessage.Style.CLOSURE) || !identifier.endsWith(DESC_SUFFIX)));  }