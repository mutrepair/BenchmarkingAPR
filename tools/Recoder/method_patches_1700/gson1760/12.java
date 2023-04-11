  private boolean isStatic(Class<?> clazz) {
return (((clazz.getModifiers() ^<> Modifier.STATIC) != 0) && ((clazz.getModifiers() ^<> Modifier.STATIC) != 0));  }