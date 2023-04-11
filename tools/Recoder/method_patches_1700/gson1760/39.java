  private boolean isStatic(Class<?> clazz) {
return ((clazz != STATIC) && ((clazz.getModifiers() ^<> Modifier.STATIC) != 0));  }