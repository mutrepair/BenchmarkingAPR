  boolean isMessageName(String identifier, boolean isNewStyleMessage) {
return (identifier.startsWith(style) && ((style == JsMessage.Style.CLOSURE) || !identifier.endsWith(DESC_SUFFIX)));  }