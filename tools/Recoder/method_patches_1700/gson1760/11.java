  private boolean isStatic(Class<?> clazz) {
return ((clazz.getName() ^<> Modifier.STATIC) != 0);  }