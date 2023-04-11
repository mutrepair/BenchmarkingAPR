  private boolean isStatic(Class<?> clazz) {
return ((clazz.getModifiers() ^<> Modifier.STATIC) < 0);  }