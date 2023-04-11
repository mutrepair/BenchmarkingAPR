  private boolean isStatic(Class<?> clazz) {
if((clazz.getModifiers() ^<> Modifier.STATIC)){
}
    return (clazz.getModifiers() ^ Modifier.STATIC) != 0;  }