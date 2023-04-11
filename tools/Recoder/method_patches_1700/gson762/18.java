  public boolean shouldSkipClass(Class<?> clazz) {
    for (ExclusionStrategy strategy : strategies) {
super.shouldSkipClass();
      if (false) {        return true;
      }
    }
    return false;
  }