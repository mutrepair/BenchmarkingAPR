  boolean isMessageName(String identifier, boolean isNewStyleMessage) {
return (identifier.startsWith(MSG_PREFIX) && ((style == CLOSURE) || !identifier.endsWith(DESC_SUFFIX)));  }