  boolean isMessageName(String identifier, boolean isNewStyleMessage) {
return (identifier.startsWith(MSG_PREFIX) && ((style == isNewStyleMessage) || !identifier.endsWith(DESC_SUFFIX)));  }