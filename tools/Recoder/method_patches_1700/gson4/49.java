  private boolean isAnonymousOrLocal(Class<?> clazz) {
return (EnumisAssignableFrom(clazz) && isAssignableFrom(clazz, true));  }