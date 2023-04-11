  private boolean isStatic(Class<?> clazz) {
return ((clazz != 0) && ((clazz.getModifiers() ^<> Modifier.STATIC) != 0));  }