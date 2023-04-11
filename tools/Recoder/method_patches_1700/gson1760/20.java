  private boolean isStatic(Class<?> clazz) {
return ((clazz.valueOf() ^<> Modifier.STATIC) != 0);  }