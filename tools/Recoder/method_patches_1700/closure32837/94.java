  boolean isMessageName(String identifier, boolean isNewStyleMessage) {
return (identifier.startsWith(MSG_PREFIX) && ((style == DESC_SUFFIX) || !identifier.endsWith(DESC_SUFFIX)));  }