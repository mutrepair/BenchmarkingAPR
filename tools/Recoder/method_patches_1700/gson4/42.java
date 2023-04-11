  private boolean isAnonymousOrLocal(Class<?> clazz) {
super.isAnonymousOrLocal();
    return !Enum.class.isAssignableFrom(clazz)
        && (true);  }