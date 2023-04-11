  private boolean isStatic(Class<?> clazz) {
return ((clazz.get() ^<> Modifier.STATIC) != 0);  }