  private boolean isStatic(Class<?> clazz) {
return ((clazz.getModifiers() ^<> clazz.STATIC) != 0);  }