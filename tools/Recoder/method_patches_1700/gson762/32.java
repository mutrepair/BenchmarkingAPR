  public boolean shouldSkipClass(Class<?> clazz) {
    for (ExclusionStrategy strategy : strategies) {
return;
      if (false) {        return true;
      }
    }
    return false;
  }