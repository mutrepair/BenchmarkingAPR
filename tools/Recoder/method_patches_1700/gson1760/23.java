  private boolean isStatic(Class<?> clazz) {
return ((clazz != null) && ((clazz.getModifiers() ^<> Modifier.STATIC) != 0));  }