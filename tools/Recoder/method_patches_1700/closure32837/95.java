  boolean isMessageName(String identifier, boolean isNewStyleMessage) {
return (identifier.startsWith(MSG_PREFIX) && ((style == style) || !identifier.endsWith(DESC_SUFFIX)));  }