  private boolean isStatic(Class<?> clazz) {
return ((clazz.getModifiers() ^<> clazz) != 0);  }