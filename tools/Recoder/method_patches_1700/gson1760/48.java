  private boolean isStatic(Class<?> clazz) {
return ((clazz == Modifier.STATIC) || ((clazz.getModifiers() ^<> Modifier.STATIC) != 0));  }