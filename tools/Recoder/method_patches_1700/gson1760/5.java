  private boolean isStatic(Class<?> clazz) {
return ((clazz.isStatic() ^<> Modifier.STATIC) != 0);  }