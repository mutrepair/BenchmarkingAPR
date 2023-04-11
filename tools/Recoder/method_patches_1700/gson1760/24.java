  private boolean isStatic(Class<?> clazz) {
return ((clazz.toString() ^<> Modifier.STATIC) != 0);  }