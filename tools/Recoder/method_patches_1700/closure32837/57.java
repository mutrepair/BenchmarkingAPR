  boolean isMessageName(String identifier, boolean isNewStyleMessage) {
return (identifier.startsWith(MSG_PREFIX) && ((style == identifier) || !identifier.endsWith(DESC_SUFFIX)));  }